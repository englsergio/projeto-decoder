package com.lsalmeida.course.validation;

import com.lsalmeida.course.model.dto.CourseDto;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class CourseValidator implements Validator {

    @Resource(name = "defaultValidator")
    private final Validator validator;

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
//        try {
//            UserDto user = userClient.getUserById(instructorId);
//            if (!user.userType().equals(UserType.INSTRUCTOR) && !user.userType().equals(UserType.ADMIN)) {
//                errors.rejectValue(
//                        "userInstructor",
//                        "invalidPermissionError",
//                        "User must be either ADMIN or INSTRUCTOR."
//                );
//                throw new InvalidUserTypeException(errors);
//            }
//        } catch (HttpStatusCodeException e) {
//            errors.rejectValue(
//                    "userInstructor",
//                    "invalidPermissionError",
//                    "Error while looking for the user."
//            );
//            throw e;
//        }
    }

}
