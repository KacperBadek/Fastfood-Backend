package com.example.tbkproject.exceptions.exception.order;

public class OrderWithSessionIdNotFoundException extends RuntimeException {
    public OrderWithSessionIdNotFoundException(String sessionId) {
        super(String.format("Order with session id: %s not found", sessionId));
    }
}
