package com.lsalmeida.course.exception;

import lombok.Getter;

@Getter
public class UserAlreadyInCourseException extends RuntimeException {

    private String message = "O usuário já está matriculado no curso.";

    public UserAlreadyInCourseException(String message) {
        super(message);
        this.message = message;
    }

    public UserAlreadyInCourseException() {
        super();
    }

}
