package com.lsalmeida.course.repository;

import com.lsalmeida.course.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserModel, UUID>, JpaSpecificationExecutor<UserModel> {

    boolean existsByUserId(UUID userId);

    @Query(value = """
    SELECT 
        CASE 
        WHEN count(tcu) > 0 THEN true 
        ELSE false 
        END
    FROM tb_courses_users tcu
    WHERE tcu.course_id = :courseId AND tcu.user_id = :userId;
    """, nativeQuery = true)
    boolean existsByCourseAndUserId(@Param("courseId") UUID courseId, @Param("userId") UUID userId);

    @Modifying
    @Query(value = "INSERT INTO tb_courses_user VALUES (:courseId, :userId);", nativeQuery = true)
    void saveCourseUser(@Param("courseId") UUID courseId, @Param("userId") UUID userId);

}