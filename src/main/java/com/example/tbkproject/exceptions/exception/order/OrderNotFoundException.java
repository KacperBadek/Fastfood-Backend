package com.example.tbkproject.exceptions.exception.order;

public class OrderNotFoundException extends Exception {
    public OrderNotFoundException(int orderNumber) {
        super(String.format("Order number %d not found", orderNumber));
    }
}
