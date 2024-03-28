package com.lsalmeida.authuser.exception;

import lombok.Getter;

@Getter
public class IncorrectUserPasswordException extends RuntimeException {

    private String message;

    public IncorrectUserPasswordException() {
        super();
    }

    public IncorrectUserPasswordException(String message) {
        super();
        this.message = message;
    }

}
