package com.lsalmeida.course.model.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record LessonDto(
        UUID lessonId,
        @NotBlank
        String title,
        String description,
        @NotBlank
        String videoUrl
) {
}
