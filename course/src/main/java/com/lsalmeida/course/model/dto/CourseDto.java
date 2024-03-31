package com.lsalmeida.course.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.lsalmeida.course.enums.CourseLevel;
import com.lsalmeida.course.enums.CourseStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record CourseDto(
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
