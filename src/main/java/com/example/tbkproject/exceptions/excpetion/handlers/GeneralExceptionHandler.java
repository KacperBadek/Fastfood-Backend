package com.example.tbkproject.exceptions.excpetion.handlers;

import com.example.tbkproject.controllers.GeneralController;
import com.example.tbkproject.exceptions.exception.general.TotalPriceMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(assignableTypes = GeneralController.class)
public class GeneralExceptionHandler {

    @ExceptionHandler(TotalPriceMismatchException.class)
    public ResponseEntity<String> handleTotalPriceMismatchException(Exception ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }
}
