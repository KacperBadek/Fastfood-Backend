package com.example.tbkproject.exceptions.exception.addon;

public class AddOnNotFoundException extends RuntimeException {
    public AddOnNotFoundException(String addOnName) {
        super(String.format("AddOn %s not found", addOnName));
    }
}
