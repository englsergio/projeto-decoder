package com.lsalmeida.course.exception;

import lombok.Getter;

@Getter
public class ModuleNotFoundException extends RuntimeException {

    private String message = "Modulo não encontrado.";

    public ModuleNotFoundException(String message) {
        super(message);
        this.message = message;
    }

    public ModuleNotFoundException() {
        super();
    }

}
