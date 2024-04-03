package com.lsalmeida.course.service;

import com.lsalmeida.course.model.CourseModel;
import com.lsalmeida.course.model.CourseUserModel;
import com.lsalmeida.course.model.dto.SubscriptionDto;
import com.lsalmeida.course.model.dto.UserDto;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface CourseUserService {

    Page<UserDto> getAllCoursesByUser(UUID userId, Pageable pageable);

    CourseUserModel saveSubscriptionUserInCourse(UUID courseId, SubscriptionDto subscriptionDto);

    void deleteCourseUserByUser(UUID userId);

    boolean existsByCourseAndUserId(CourseModel courseModel, @NotNull UUID userId);

    boolean existsByUserId(@NotNull UUID userId);
}
