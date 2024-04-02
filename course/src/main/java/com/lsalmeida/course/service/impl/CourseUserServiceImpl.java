package com.lsalmeida.course.service.impl;

import com.lsalmeida.course.client.UserClient;
import com.lsalmeida.course.enums.UserStatus;
import com.lsalmeida.course.exception.CourseNotFoundException;
import com.lsalmeida.course.exception.UserAlreadyInCourseException;
import com.lsalmeida.course.exception.UserBlockedException;
import com.lsalmeida.course.model.CourseModel;
import com.lsalmeida.course.model.CourseUserModel;
import com.lsalmeida.course.model.dto.CourseUserDto;
import com.lsalmeida.course.model.dto.SubscriptionDto;
import com.lsalmeida.course.model.dto.UserDto;
import com.lsalmeida.course.repository.CourseRepository;
import com.lsalmeida.course.repository.CourseUserRepository;
import com.lsalmeida.course.service.CourseUserService;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CourseUserServiceImpl implements CourseUserService {

    private final UserClient userClient;
    private final CourseRepository courseRepository;
    private final CourseUserRepository courseUserRepository;

    @Override
    public Page<UserDto> getAllCoursesByUser(UUID userId, Pageable pageable) {
        return userClient.getAllUsersByCourse(userId, pageable);
    }

    @Transactional
    @Override
    public CourseUserModel saveSubscriptionUserInCourse(UUID courseId, SubscriptionDto subscriptionDto) {
        CourseModel courseModel = courseRepository.findById(courseId)
                .orElseThrow(CourseNotFoundException::new);
        if (existsByCourseAndUserId(courseModel, subscriptionDto.userId())) throw new UserAlreadyInCourseException();
        UserDto user = userClient.getUserById(subscriptionDto.userId());
        if (user.userStatus().equals(UserStatus.BLOCKED)) throw new UserBlockedException();
        CourseUserModel saved = courseUserRepository.save(new CourseUserModel(null, courseModel, subscriptionDto.userId()));
        userClient.assignCourseToUser(new CourseUserDto(subscriptionDto.userId(), courseId));
        return saved;
    }

    public boolean existsByCourseAndUserId(CourseModel courseModel, @NotNull UUID userId) {
        return courseUserRepository.existsByCourseAndUserId(courseModel, userId);
    }

}
