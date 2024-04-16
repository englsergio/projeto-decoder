package com.lsalmeida.course.repository;

import com.lsalmeida.course.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserModel, UUID>, JpaSpecificationExecutor<UserModel> {
//    boolean existsByCourseAndUserId(CourseModel course, UUID userId);

    boolean existsByUserId(UUID userId);

    void deleteCourseUserByUserId(UUID userId);

}