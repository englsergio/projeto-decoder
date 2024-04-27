package com.lsalmeida.authuser.exception;

import lombok.Getter;

@Getter
public class RoleNotFoundException extends RuntimeException {

    private String message = "Role não encontrada.";

    public RoleNotFoundException() {
        super();
    }

    public RoleNotFoundException(String message) {
        super(message);
        this.message = message;
    }

}
