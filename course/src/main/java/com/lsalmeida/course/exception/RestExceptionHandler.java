package com.lsalmeida.course.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpStatusCodeException;

@RequiredArgsConstructor
@ControllerAdvice
public class RestExceptionHandler {

    private final ObjectMapper objectMapper;

    @ExceptionHandler(value = CourseNotFoundException.class)
    public ResponseEntity<ErrorModel> handleCourseNotFoundException(CourseNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorModel(e.getMessage()));
    }

    @ExceptionHandler(value = UserAlreadyInCourseException.class)
    public ResponseEntity<ErrorModel> handleUserAlreadyInCourseException(UserAlreadyInCourseException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorModel(e.getMessage()));
    }

    @ExceptionHandler(value = UserBlockedException.class)
    public ResponseEntity<ErrorModel> handleUserBlockedException(UserBlockedException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorModel(e.getMessage()));
    }

    @ExceptionHandler(value = HttpStatusCodeException.class)
    public ResponseEntity<ErrorModel> handleHttpStatusCodeException(HttpStatusCodeException e) {
        return ResponseEntity.status(e.getStatusCode()).body(new ErrorModel(e.getMessage()));
    }

    @ExceptionHandler(value = InvalidUserTypeException.class)
    public ResponseEntity<Errors> handleInvalidUserTypeException(InvalidUserTypeException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getErrors());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorModel> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorModel(e.getMessage()));
    }

}
