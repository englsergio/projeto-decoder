package com.lsalmeida.course.validation;

import com.lsalmeida.course.enums.UserType;
import com.lsalmeida.course.model.UserModel;
import com.lsalmeida.course.model.dto.CourseDto;
import com.lsalmeida.course.service.UserService;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class CourseValidator implements Validator {

    @Resource(name = "defaultValidator")
    private final Validator validator;
    private final UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(CourseDto.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CourseDto courseDto = (CourseDto) target;
        validator.validate(courseDto, errors);
        if (!errors.hasErrors())
            validateUserInstructor(courseDto.courseInstructor(), errors);
    }

    private void validateUserInstructor(UUID instructorId, Errors errors) {
        Optional<UserModel> instructorOptional = userService.findById(instructorId);
        if (instructorOptional.isEmpty()) {
            errors.rejectValue("userInstructor",
                    "userInstructorError",
                    "Instructor not found.");
        } else {
            String userType = instructorOptional.map(UserModel::getUserType).orElse("");
            if (!userType.equals(UserType.INSTRUCTOR.name()) && !userType.equals(UserType.ADMIN.name()))
                errors.rejectValue(
                        "userInstructor",
                        "invalidPermissionError",
                        "User must be either ADMIN or INSTRUCTOR.");
        }
    }

}
