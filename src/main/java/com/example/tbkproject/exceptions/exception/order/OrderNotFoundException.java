package com.example.tbkproject.exceptions.exception.order;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(String id) {
        super(String.format("Order with id: %s not found", id));
    }
}
