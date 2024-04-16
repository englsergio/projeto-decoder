package com.lsalmeida.authuser.services.impl;

import com.lsalmeida.authuser.enums.UserType;
import com.lsalmeida.authuser.mapper.UserMapper;
import com.lsalmeida.authuser.model.UserModel;
import com.lsalmeida.authuser.model.dto.UserDto;
import com.lsalmeida.authuser.services.InstructorService;
import com.lsalmeida.authuser.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class InstructorServiceImpl implements InstructorService {

    private final UserService userService;
    private final UserMapper userMapper;

    @Override
    public UserDto turnIntoInstructor(UUID userId) {
        UserModel userModel = userService.findById(userId);
        userModel.setUserType(UserType.INSTRUCTOR);
        userModel.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
        return userMapper.toDto(userService.updateUser(userModel));
    }

}
