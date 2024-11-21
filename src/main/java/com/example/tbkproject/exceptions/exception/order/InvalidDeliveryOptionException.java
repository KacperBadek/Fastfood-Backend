package com.example.tbkproject.exceptions.exception.order;

public class InvalidDeliveryOptionException extends RuntimeException {
    public InvalidDeliveryOptionException(String value) {
        super(String.format("Invalid delivery option: %s", value));
    }
}
