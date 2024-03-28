package com.lsalmeida.authuser.exception;

import lombok.Getter;

@Getter
public class UserAlreadyRegisteredException extends RuntimeException {

    private String message;

    public UserAlreadyRegisteredException() {
       super();
    }

    public UserAlreadyRegisteredException(String message) {
        super();
        this.message = message;
    }

}
