package com.lsalmeida.course.repository;

import com.lsalmeida.course.model.CourseModel;
import com.lsalmeida.course.model.CourseUserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CourseUserRepository extends JpaRepository<CourseUserModel, UUID> {

    boolean existsByCourseAndUserId(CourseModel course, UUID userId);
}