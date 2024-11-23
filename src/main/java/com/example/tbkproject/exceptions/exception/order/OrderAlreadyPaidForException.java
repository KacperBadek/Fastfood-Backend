package com.example.tbkproject.exceptions.exception.order;

public class OrderAlreadyPaidForException extends RuntimeException {
    public OrderAlreadyPaidForException(String id) {
        super(String.format("Order: %s, can't be cancelled anymore.", id));
    }
}
