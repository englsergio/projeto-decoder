package com.lsalmeida.course.service.impl;

import com.lsalmeida.course.enums.UserStatus;
import com.lsalmeida.course.exception.CourseNotFoundException;
import com.lsalmeida.course.exception.UserAlreadyInCourseException;
import com.lsalmeida.course.exception.UserBlockedException;
import com.lsalmeida.course.model.CourseModel;
import com.lsalmeida.course.model.UserModel;
import com.lsalmeida.course.model.dto.SubscriptionDto;
import com.lsalmeida.course.repository.CourseRepository;
import com.lsalmeida.course.repository.UserRepository;
import com.lsalmeida.course.service.CourseService;
import com.lsalmeida.course.service.UserService;
import com.lsalmeida.course.specification.SpecificationTemplate;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final CourseRepository courseRepository;
    private final CourseService courseService;
    private final UserRepository userRepository;

    @Override
    public Page<UserModel> findAll(UUID courseId, SpecificationTemplate.UserSpec spec, Pageable pageable) {
        if (!courseService.existsById(courseId)) throw new CourseNotFoundException();
        return userRepository.findAll(SpecificationTemplate.userCourseId(courseId).and(spec), pageable);
    }

    @Transactional
    @Override
    public void saveSubscriptionUserInCourse(UUID courseId, SubscriptionDto subscriptionDto) {
        CourseModel courseModel = courseRepository.findById(courseId)
                .orElseThrow(CourseNotFoundException::new);
        UserModel userModel = userRepository.findById(subscriptionDto.userId())
                .orElseThrow(CourseNotFoundException::new);
        if (existsByCourseAndUserId(courseModel, subscriptionDto.userId())) throw new UserAlreadyInCourseException();
        if (userModel.getUserStatus().equals(UserStatus.BLOCKED.name()))
            throw new UserBlockedException();
        userRepository.saveCourseUser(courseId, userModel.getUserId());
    }

    @Override
    public boolean existsByCourseAndUserId(CourseModel courseModel, @NotNull UUID userId) {
        return userRepository.existsByCourseAndUserId(courseModel.getCourseId(), userId);
    }

    @Override
    public boolean existsByUserId(@NotNull UUID userId) {
        return userRepository.existsByUserId(userId);
    }

    @Override
    public UserModel save(UserModel userModel) {
        return userRepository.save(userModel);
    }

    @Transactional
    @Override
    public void delete(UUID userId) {
        courseRepository.deleteCourseUserByUser(userId);
        userRepository.deleteById(userId);
    }

    @Override
    public Optional<UserModel> findById(UUID userId) {
        return userRepository.findById(userId);
    }

}
