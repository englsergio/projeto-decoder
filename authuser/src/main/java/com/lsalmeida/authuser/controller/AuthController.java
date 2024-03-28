package com.lsalmeida.authuser.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.lsalmeida.authuser.model.dto.UserDto;
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

    @Validated(UserDto.UserView.RegistrationPost.class)
    @PostMapping("/signup")
    public ResponseEntity<UserDto> registerUser(
            @RequestBody @Valid @JsonView(UserDto.UserView.RegistrationPost.class) UserDto dto) {
        userService.existsByUsername(dto.username());
        userService.existsByEmail(dto.email());
        UserDto responseDto = userService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }
}
