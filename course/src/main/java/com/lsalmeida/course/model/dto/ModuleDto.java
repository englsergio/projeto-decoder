package com.lsalmeida.course.model.dto;

import jakarta.validation.constraints.NotBlank;

public record ModuleDto(
        @NotBlank
        String title,
        @NotBlank
        String description
) {
}
