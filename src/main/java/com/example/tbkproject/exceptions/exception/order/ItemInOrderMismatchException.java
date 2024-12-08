package com.example.tbkproject.exceptions.exception.order;

public class ItemInOrderMismatchException extends RuntimeException {
    public ItemInOrderMismatchException(String itemName) {
        super(String.format("Item in order mismatch exception for %s", itemName));
    }
}
