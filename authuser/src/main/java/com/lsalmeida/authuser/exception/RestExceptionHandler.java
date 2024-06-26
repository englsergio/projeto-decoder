package com.lsalmeida.authuser.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<ErrorModel> handleUserNotFoundException(UserNotFoundException e) {
        ErrorModel errorModel = new ErrorModel(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorModel);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = RoleNotFoundException.class)
    public ResponseEntity<ErrorModel> handleRoleNotFoundException(RoleNotFoundException e) {
        ErrorModel errorModel = new ErrorModel(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorModel);
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(value = UserAlreadyRegisteredException.class)
    public ResponseEntity<ErrorModel> handleUserAlreadyRegisteredException(UserAlreadyRegisteredException e) {
        ErrorModel errorModel = new ErrorModel(e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorModel);
    }

    @ExceptionHandler(value = CourseNotFoundException.class)
    public ResponseEntity<ErrorModel> handleCourseNotFoundException(CourseNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorModel(e.getMessage()));
    }

}
