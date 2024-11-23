package com.example.tbkproject.exceptions.exception.general;

public class TotalPriceMismatchException extends RuntimeException {
    public TotalPriceMismatchException() {
        super("Mismatch between orderTotalPrice and paymentTotalPrice");
    }
}
