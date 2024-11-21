package com.example.tbkproject.exceptions.excpetion.handlers;

import com.example.tbkproject.exceptions.exception.table.TableNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice(assignableTypes = TableExceptionHandler.class)
public class TableExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(TableNotFoundException.class)
    public ResponseEntity<String> handleTableNotFountException(Exception ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

}
