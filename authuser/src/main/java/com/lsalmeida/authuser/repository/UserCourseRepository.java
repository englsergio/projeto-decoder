package com.lsalmeida.authuser.repository;

import com.lsalmeida.authuser.model.UserCourseModel;
import com.lsalmeida.authuser.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface UserCourseRepository extends JpaRepository<UserCourseModel, UUID> {

    boolean existsByUserAndCourseId(UserModel user, UUID courseId);

    @Modifying
    @Query(value = "delete from tb_users_courses where user_id = :userId", nativeQuery = true)
    void deleteUserCourseIntoUser(@Param("userId") UUID userId);
}
