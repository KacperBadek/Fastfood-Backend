package com.example.tbkproject.exceptions.exception.product;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String name) {
        super(String.format("Product: '%s', not found.", name));
    }
}
