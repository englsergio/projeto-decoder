package com.lsalmeida.authuser.services;

import com.lsalmeida.authuser.model.UserCourseModel;
import com.lsalmeida.authuser.model.dto.CourseDto;
import com.lsalmeida.authuser.model.dto.UserCourseDto;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface UserCourseService {
    Page<CourseDto> getAllCoursesByUser(UUID userId, Pageable pageable);
    UserCourseModel saveSubscriptionCourseInUser(UUID userId, @Valid UserCourseDto subscriptionDto);
}
