package com.lsalmeida.authuser.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.lsalmeida.authuser.model.UserModel;
import com.lsalmeida.authuser.model.dto.UserDto;
import com.lsalmeida.authuser.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserModel>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserModel> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @Validated(UserDto.UserView.RegistrationPost.class)
    @PostMapping
    public ResponseEntity<UserDto> save(
            @RequestBody @Valid @JsonView(UserDto.UserView.RegistrationPost.class) UserDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(dto));
    }

    @Validated(UserDto.UserView.UserPut.class)
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(
            @PathVariable UUID id,
            @RequestBody @Valid @JsonView(UserDto.UserView.UserPut.class) UserDto dto) {
        UserDto saved = userService.updateUser(id, dto);
        return ResponseEntity.ok(saved);
    }

    @Validated(UserDto.UserView.PasswordPut.class)
    @PutMapping("/{id}/password")
    public ResponseEntity<Void> updatePassword(
            @PathVariable UUID id,
            @RequestBody @Valid @JsonView(UserDto.UserView.PasswordPut.class) UserDto dto) {
        userService.updatePassword(id, dto);
        return ResponseEntity.ok().build();
    }

    @Validated(UserDto.UserView.ImagePut.class)
    @PutMapping("/{id}/image")
    public ResponseEntity<UserDto> updateImage(
            @PathVariable UUID id,
            @RequestBody @Valid @JsonView(UserDto.UserView.ImagePut.class) UserDto dto) {
        UserDto updated = userService.updateImage(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        UserModel user = userService.findById(id);
        userService.delete(user);
        return ResponseEntity.ok().build();
    }

}
