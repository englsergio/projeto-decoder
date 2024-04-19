package com.lsalmeida.course.model.dto;

import java.util.UUID;

public record NotificationCommand(
        String title,
        String message,
        UUID userId
){
}
