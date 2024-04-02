package com.lsalmeida.authuser.controller;

import com.lsalmeida.authuser.enums.UserType;
import com.lsalmeida.authuser.model.UserModel;
import com.lsalmeida.authuser.model.dto.InstructorDto;
import com.lsalmeida.authuser.model.dto.UserDto;
import com.lsalmeida.authuser.services.InstructorService;
import com.lsalmeida.authuser.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;

@RequiredArgsConstructor
@RestController
@RequestMapping("/instructors")
@CrossOrigin(origins = "*", maxAge = 3600)
public class InstructorController {

    private final InstructorService instructorService;

    @PutMapping("/subscription")
    public ResponseEntity<UserDto> saveSubscriptionInstructor(@Valid @RequestBody InstructorDto dto) {
        UserDto updated = instructorService.turnIntoInstructor(dto.userId());
        return ResponseEntity.ok(updated);
    }

}
