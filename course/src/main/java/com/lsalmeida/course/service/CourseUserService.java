package com.lsalmeida.course.service;

import com.lsalmeida.course.model.CourseUserModel;
import com.lsalmeida.course.model.dto.SubscriptionDto;
import com.lsalmeida.course.model.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface CourseUserService {

    Page<UserDto> getAllCoursesByUser(UUID userId, Pageable pageable);

    CourseUserModel saveSubscriptionUserInCourse(UUID courseId, SubscriptionDto subscriptionDto);
}
