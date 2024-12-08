package com.example.tbkproject.exceptions.exception.order;

public class AddOnInItemMismatchException extends RuntimeException {
    public AddOnInItemMismatchException(String addOnName) {
        super(String.format("AddOn in item mismatch exception for %s", addOnName));
    }
}
