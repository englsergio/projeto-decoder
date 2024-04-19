package com.lsalmeida.notification.model.dto;

import java.util.UUID;

public record NotificationCommand (
        String title,
        String message,
        UUID userId
){
}
