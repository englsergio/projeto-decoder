package com.lsalmeida.authuser.services;

import com.lsalmeida.authuser.model.UserModel;
import com.lsalmeida.authuser.model.dto.UserDto;

import java.util.List;
import java.util.UUID;

public interface UserService {

    List<UserModel> findAll();
    UserModel findById(UUID id);
    UserDto save(UserDto user);
    void delete(UserModel user);
    void existsByUsername(String username);
    void existsByEmail(String email);
    UserDto updateUser(UUID id, UserDto dto);
    void updatePassword(UUID id, UserDto dto);
    UserDto updateImage(UUID id, UserDto dto);
}
