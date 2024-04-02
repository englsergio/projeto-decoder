package com.lsalmeida.course.controller;

import com.lsalmeida.course.model.CourseUserModel;
import com.lsalmeida.course.model.dto.SubscriptionDto;
import com.lsalmeida.course.model.dto.UserDto;
import com.lsalmeida.course.service.CourseUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping
@CrossOrigin(origins = "*", maxAge = 3600)
public class CourseUserController {

    private final CourseUserService courseUserService;

    @GetMapping("/courses/{courseId}/users")
    public ResponseEntity<Page<UserDto>> findAll(
            @PageableDefault(sort = "userId", direction = Sort.Direction.ASC) Pageable pageable,
            @PathVariable(required = false) UUID courseId) {
        return ResponseEntity.ok(courseUserService.getAllCoursesByUser(courseId, pageable));
    }

    @PostMapping("/courses/{courseId}/users/subscription")
    public ResponseEntity<CourseUserModel> saveSubscriptionUserInCourse(
            @Valid @RequestBody SubscriptionDto subscriptionDto,
            @PathVariable(required = false) UUID courseId) {
        CourseUserModel courseUserModel = courseUserService.saveSubscriptionUserInCourse(courseId, subscriptionDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(courseUserModel);
    }

}
