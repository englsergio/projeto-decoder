package com.lsalmeida.course.exception;

import lombok.Getter;

@Getter
public class UserNotFoundException extends RuntimeException {

    private String message = "Usuário não encontrado.";

    public UserNotFoundException(String message) {
        super(message);
        this.message = message;
    }

    public UserNotFoundException() {
        super();
    }

}
