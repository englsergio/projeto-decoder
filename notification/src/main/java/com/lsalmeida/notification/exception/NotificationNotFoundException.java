package com.lsalmeida.notification.exception;

import lombok.Getter;

@Getter
public class NotificationNotFoundException extends RuntimeException {

    private String message = "Notification not found!";

    public NotificationNotFoundException(String message) {
        super(message);
        this.message = message;
    }

    public NotificationNotFoundException() {
        super();
    }

}
