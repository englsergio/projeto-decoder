package com.lsalmeida.notification.model.dto;

import com.lsalmeida.notification.enums.NotificationStatus;
import jakarta.validation.constraints.NotNull;

public record NotificationDto(
        @NotNull NotificationStatus status
        )
{}
