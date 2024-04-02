package com.lsalmeida.course.model.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record ModuleDto(
        UUID moduleId,
        @NotBlank
        String title,
        @NotBlank
        String description
) {
}
