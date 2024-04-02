package com.lsalmeida.authuser.exception;

import lombok.Getter;

@Getter
public class CourseNotFoundException extends RuntimeException {

    private String message = "Curso não encontrado.";

    public CourseNotFoundException() {
        super();
    }

    public CourseNotFoundException(String message) {
        super(message);
        this.message = message;
    }

}
