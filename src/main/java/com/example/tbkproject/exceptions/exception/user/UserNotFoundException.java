package com.example.tbkproject.exceptions.exception.user;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(String email) {
        super(String.format("User with email: %s not found", email));
    }
}
