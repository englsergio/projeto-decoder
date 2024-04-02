package com.lsalmeida.authuser.model.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record InstructorDto(
        @NotNull
        UUID userId
) {
}
