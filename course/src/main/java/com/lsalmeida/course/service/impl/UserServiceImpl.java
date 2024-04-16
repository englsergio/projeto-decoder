package com.lsalmeida.course.service.impl;

import com.lsalmeida.course.exception.CourseNotFoundException;
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

    // TODO: incluir verificações event carried state transfer
    @Transactional
    @Override
    public UserModel saveSubscriptionUserInCourse(UUID courseId, SubscriptionDto subscriptionDto) {
//        CourseModel courseModel = courseRepository.findById(courseId)
//                .orElseThrow(CourseNotFoundException::new);
//        if (existsByCourseAndUserId(courseModel, subscriptionDto.userId())) throw new UserAlreadyInCourseException();
//        return saved;
        return null;
    }

//    @Override
//    public boolean existsByCourseAndUserId(CourseModel courseModel, @NotNull UUID userId) {
//        return userRepository.existsByCourseAndUserId(courseModel, userId);
//    }

    @Override
    public boolean existsByUserId(@NotNull UUID userId) {
        return userRepository.existsByUserId(userId);
    }

    @Override
    public UserModel save(UserModel userModel) {
        return userRepository.save(userModel);
    }

}
