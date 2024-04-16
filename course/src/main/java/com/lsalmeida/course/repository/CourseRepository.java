package com.lsalmeida.course.repository;

import com.lsalmeida.course.model.CourseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface CourseRepository extends JpaRepository<CourseModel, UUID>, JpaSpecificationExecutor<CourseModel> {
    @Modifying
    @Query(value = "DELETE FROM tb_courses_users WHERE user_id = :userId", nativeQuery = true)
    void deleteCourseUserByUser(@Param("userId") UUID userId);

    @Modifying
    @Query(value = "DELETE FROM tb_courses_users WHERE course_id = :courseId", nativeQuery = true)
    void deleteCourseUserByCourse(@Param("courseId") UUID courseId);

}