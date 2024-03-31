package com.lsalmeida.course.service.impl;

import com.lsalmeida.course.repository.CourseUserRepository;
import com.lsalmeida.course.service.CourseUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CourseUserServiceImpl implements CourseUserService {

    private final CourseUserRepository courseUserRepository;
}
