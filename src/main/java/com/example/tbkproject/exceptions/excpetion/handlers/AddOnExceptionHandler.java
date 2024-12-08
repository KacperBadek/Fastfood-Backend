package com.example.tbkproject.exceptions.excpetion.handlers;

import com.example.tbkproject.controllers.OrdersController;
import com.example.tbkproject.exceptions.exception.addon.AddOnNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(assignableTypes = OrdersController.class)
public class AddOnExceptionHandler {

    @ExceptionHandler(AddOnNotFoundException.class)
    public ResponseEntity<String> handleAddOnNotFoundException(AddOnNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
