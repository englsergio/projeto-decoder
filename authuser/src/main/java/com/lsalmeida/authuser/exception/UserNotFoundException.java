package com.lsalmeida.authuser.exception;

import lombok.Getter;

@Getter
public class UserNotFoundException extends RuntimeException {

    private String message;

    public UserNotFoundException() {
        super();
    }

    public UserNotFoundException(String message) {
        super(message);
        this.message = message;
    }

}
