package com.lsalmeida.course.exception;

import lombok.Getter;
import org.springframework.validation.Errors;

@Getter
public class InvalidUserTypeException extends RuntimeException {

    private String message = "Usuário possui um tipo inválido para a operação.";
    private Errors errors;

    public InvalidUserTypeException(String message) {
        super(message);
        this.message = message;
    }

    public InvalidUserTypeException() {
        super();
    }

    public InvalidUserTypeException(Errors errors) {
        super();
        this.errors = errors;
    }
}
