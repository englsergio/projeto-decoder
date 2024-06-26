package com.lsalmeida.authuser.controller;

import com.lsalmeida.authuser.model.dto.CourseDto;
import com.lsalmeida.authuser.services.UserCourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserCourseController {

    private final UserCourseService userCourseService;

    @GetMapping("/users/{userId}/courses")
    public ResponseEntity<Page<CourseDto>> getAllCoursesByUser(
            @PageableDefault(sort = "courseId", direction = Sort.Direction.ASC) Pageable pageable,
            @PathVariable(required = false) UUID userId) {
        return ResponseEntity.ok(userCourseService.getAllCoursesByUser(userId, pageable));
    }

}
