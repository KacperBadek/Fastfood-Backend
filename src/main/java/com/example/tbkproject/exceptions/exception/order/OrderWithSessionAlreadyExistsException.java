package com.example.tbkproject.exceptions.exception.order;

public class OrderWithSessionAlreadyExistsException extends RuntimeException {
    public OrderWithSessionAlreadyExistsException() {
        super("Order in this session was already created!");
    }
}
