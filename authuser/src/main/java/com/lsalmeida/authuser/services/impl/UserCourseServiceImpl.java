package com.lsalmeida.authuser.services.impl;

import com.lsalmeida.authuser.client.CourseClient;
import com.lsalmeida.authuser.enums.UserStatus;
import com.lsalmeida.authuser.exception.UserAlreadyRegisteredException;
import com.lsalmeida.authuser.exception.UserBlockedException;
import com.lsalmeida.authuser.model.UserCourseModel;
import com.lsalmeida.authuser.model.UserModel;
import com.lsalmeida.authuser.model.dto.CourseDto;
import com.lsalmeida.authuser.model.dto.UserCourseDto;
import com.lsalmeida.authuser.repository.UserCourseRepository;
import com.lsalmeida.authuser.services.UserCourseService;
import com.lsalmeida.authuser.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserCourseServiceImpl implements UserCourseService {

    private final CourseClient courseClient;
    private final UserService userService;
    private final UserCourseRepository userCourseRepository;

    @Override
    public Page<CourseDto> getAllCoursesByUser(UUID userId, Pageable pageable) {
        return courseClient.getAllCoursesByUser(userId, pageable);
    }

    @Override
    public UserCourseModel saveSubscriptionCourseInUser(UUID userId, UserCourseDto userCourseDto) {
        UserModel userModel = userService.findById(userId);
        if (userModel.getUserStatus().equals(UserStatus.BLOCKED)) throw new UserBlockedException();
        if (userCourseRepository.existsByUserAndCourseId(userModel, userCourseDto.courseId()))
            throw new UserAlreadyRegisteredException();
        return userCourseRepository.save(new UserCourseModel(null, userModel, userCourseDto.courseId()));
    }

}
