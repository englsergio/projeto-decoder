package com.lsalmeida.authuser.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.lsalmeida.authuser.enums.CourseLevel;
import com.lsalmeida.authuser.enums.CourseStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record CourseDto(
        UUID courseId,
        @NotBlank
        String name,
        @NotBlank
        String description,
        String imageUrl,
        @NotNull
        CourseStatus courseStatus,
        @NotNull
        UUID courseInstructor,
        @NotNull
        CourseLevel courseLevel
) {
}
