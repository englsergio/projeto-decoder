package com.lsalmeida.authuser.services;

import com.lsalmeida.authuser.model.dto.UserDto;

import java.util.UUID;

public interface InstructorService {
    UserDto turnIntoInstructor(UUID userId);
}
