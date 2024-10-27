package com.example.tbkproject.exceptions.exception.product;

public class ProductNotFoundException extends Exception {
    public ProductNotFoundException(String name) {super(String.format("Product: '%s', not found.", name));}
}
