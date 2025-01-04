package com.example.tbkproject.exceptions.excpetion.handlers;

import com.example.tbkproject.controllers.OrdersController;
import com.example.tbkproject.exceptions.exception.order.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice(assignableTypes = OrdersController.class)
public class OrderExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<String> handleOrderNotFoundException(Exception ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(OrderWithSessionIdNotFoundException.class)
    public ResponseEntity<String> handleOrderWithSessionIdNotFoundException(Exception ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(InvalidDeliveryOptionException.class)
    public ResponseEntity<String> handleInvalidDeliveryOptionException(Exception ex) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(ex.getMessage());
    }

    @ExceptionHandler(OrderAlreadyPaidForException.class)
    public ResponseEntity<String> handleOrderAlreadyPaidForException(Exception ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(OrderWithSessionAlreadyExistsException.class)
    public ResponseEntity<String> handleOrderWithSessionAlreadyExistsException(Exception ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }
}
