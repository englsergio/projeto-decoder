package com.lsalmeida.course.exception;

import lombok.Getter;

@Getter
public class UserBlockedException extends RuntimeException {

    private String message = "O usuário está bloqueado!";

    public UserBlockedException(String message) {
        super(message);
        this.message = message;
    }

    public UserBlockedException() {
        super();
    }

}
