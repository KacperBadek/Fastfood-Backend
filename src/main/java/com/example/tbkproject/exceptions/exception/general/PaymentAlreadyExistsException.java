package com.example.tbkproject.exceptions.exception.general;

public class PaymentAlreadyExistsException extends RuntimeException{
    public PaymentAlreadyExistsException() {
        super("Order already paid for.");
    }
}
