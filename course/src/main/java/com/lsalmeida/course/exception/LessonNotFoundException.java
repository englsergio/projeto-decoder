package com.lsalmeida.course.exception;

import lombok.Getter;

@Getter
public class LessonNotFoundException extends RuntimeException {

    private String message = "Lição não encontrada.";

    public LessonNotFoundException(String message) {
        super(message);
        this.message = message;
    }

    public LessonNotFoundException() {
        super();
    }

}
