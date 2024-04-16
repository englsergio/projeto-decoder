package com.lsalmeida.course.controller;

import com.lsalmeida.course.model.UserModel;
import com.lsalmeida.course.model.dto.SubscriptionDto;
import com.lsalmeida.course.service.UserService;
import com.lsalmeida.course.specification.SpecificationTemplate;
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

    private final UserService userService;

    @GetMapping("/courses/{courseId}/users")
    public ResponseEntity<Page<UserModel>> findAll(
            SpecificationTemplate.UserSpec spec,
            @PageableDefault(sort = "userId", direction = Sort.Direction.ASC) Pageable pageable,
            @PathVariable(required = false) UUID courseId) {
        return ResponseEntity.ok(userService.findAll(courseId, spec, pageable));
    }

    @PostMapping("/courses/{courseId}/users/subscription")
    public ResponseEntity<UserModel> saveSubscriptionUserInCourse(
            @Valid @RequestBody SubscriptionDto subscriptionDto,
            @PathVariable(required = false) UUID courseId) {
        UserModel userModel = userService.saveSubscriptionUserInCourse(courseId, subscriptionDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(userModel);
    }

}
