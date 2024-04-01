package com.lsalmeida.authuser.services.impl;

import com.lsalmeida.authuser.repository.UserCourseRepository;
import com.lsalmeida.authuser.services.UserCourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserCourseServiceImpl implements UserCourseService {

    private final UserCourseRepository userCourseRepository;
}
