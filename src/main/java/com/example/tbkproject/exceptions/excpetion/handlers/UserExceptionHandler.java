package com.example.tbkproject.exceptions.excpetion.handlers;

import com.example.tbkproject.controllers.UsersController;
import com.example.tbkproject.exceptions.exception.user.InvalidCredentialsException;
import com.example.tbkproject.exceptions.exception.user.UserAlreadyExistsException;
import com.example.tbkproject.exceptions.exception.user.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice(assignableTypes = UsersController.class)
public class UserExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(Exception ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<String> handleUserAlreadyExistsException(Exception ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<String> handelInvalidCredentialsException(Exception ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
    }

}
