package com.example.tbkproject.exceptions.exception.product;

public class ProductAlreadyExistsException extends RuntimeException {
    public ProductAlreadyExistsException(String name) {
        super(String.format("Product: '%s', already exists.", name));
    }
}
