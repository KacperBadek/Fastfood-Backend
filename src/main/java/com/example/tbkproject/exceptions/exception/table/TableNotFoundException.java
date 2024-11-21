package com.example.tbkproject.exceptions.exception.table;

public class TableNotFoundException extends RuntimeException{
    public TableNotFoundException(String id) {
        super(String.format("Table with id: '%s', not found.", id));
    }
}
