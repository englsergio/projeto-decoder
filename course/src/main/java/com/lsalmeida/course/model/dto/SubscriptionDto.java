package com.lsalmeida.course.model.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record SubscriptionDto(
        @NotNull
        UUID userId
) {
}
