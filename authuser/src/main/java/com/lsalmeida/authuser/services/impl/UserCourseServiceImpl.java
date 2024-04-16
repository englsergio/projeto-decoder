package com.lsalmeida.authuser.services.impl;

import com.lsalmeida.authuser.client.CourseClient;
import com.lsalmeida.authuser.model.dto.CourseDto;
import com.lsalmeida.authuser.services.UserCourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserCourseServiceImpl implements UserCourseService {

    private final CourseClient courseClient;

    @Override
    public Page<CourseDto> getAllCoursesByUser(UUID userId, Pageable pageable) {
        return courseClient.getAllCoursesByUser(userId, pageable);
    }

}
