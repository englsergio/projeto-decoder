package com.lsalmeida.authuser.services;

import com.lsalmeida.authuser.model.UserModel;
import com.lsalmeida.authuser.model.dto.UserDto;
import com.lsalmeida.authuser.specification.SpecificationTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

public interface UserService {
    Page<UserModel> findAll(Specification<UserModel> spec, Pageable pageable);
    UserModel findById(UUID id);
    UserDto save(UserDto user);
    void delete(UserModel user);
    void existsByUsername(String username);
    void existsByEmail(String email);
    UserDto updateUser(UUID id, UserDto dto);
    void updatePassword(UUID id, UserDto dto);
    UserDto updateImage(UUID id, UserDto dto);
}
