package com.lsalmeida.course.exception;

import lombok.Getter;

@Getter
public class CourseNotFoundException extends RuntimeException {

    private String message = "Usuário não encontrado.";

    public CourseNotFoundException(String message) {
        super(message);
        this.message = message;
    }

    public CourseNotFoundException() {
        super();
    }

}
