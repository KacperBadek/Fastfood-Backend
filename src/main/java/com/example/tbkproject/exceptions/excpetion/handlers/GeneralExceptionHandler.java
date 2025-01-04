package com.example.tbkproject.exceptions.excpetion.handlers;

import com.example.tbkproject.controllers.GeneralController;
import com.example.tbkproject.exceptions.exception.general.PaymentAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice(assignableTypes = GeneralController.class)
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(PaymentAlreadyExistsException.class)
    ResponseEntity<String> handlePaymentAlreadyExistsException(Exception ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }
}
