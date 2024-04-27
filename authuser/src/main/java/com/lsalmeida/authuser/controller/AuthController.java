package com.lsalmeida.authuser.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.lsalmeida.authuser.enums.RoleType;
import com.lsalmeida.authuser.model.RoleModel;
import com.lsalmeida.authuser.model.dto.UserDto;
import com.lsalmeida.authuser.services.RoleService;
import com.lsalmeida.authuser.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final RoleService roleService;

    @Validated(UserDto.UserView.RegistrationPost.class)
    @PostMapping("/signup")
    public ResponseEntity<UserDto> registerUser(
            @RequestBody @Valid @JsonView(UserDto.UserView.RegistrationPost.class) UserDto dto) {
        userService.existsByUsername(dto.username());
        userService.existsByEmail(dto.email());
        RoleModel roleModel = roleService.findByRoleName(RoleType.ROLE_STUDENT);
        UserDto responseDto = userService.saveUserAndPublish(dto, roleModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }
}
