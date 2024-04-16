package com.lsalmeida.course.service;

import com.lsalmeida.course.model.UserModel;
import com.lsalmeida.course.model.dto.SubscriptionDto;
import com.lsalmeida.course.specification.SpecificationTemplate;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface UserService {

    Page<UserModel> findAll(UUID userId, SpecificationTemplate.UserSpec spec, Pageable pageable);

    UserModel saveSubscriptionUserInCourse(UUID courseId, SubscriptionDto subscriptionDto);

//    boolean existsByCourseAndUserId(CourseModel courseModel, @NotNull UUID userId);

    boolean existsByUserId(@NotNull UUID userId);

    UserModel save(UserModel userModel);
}
