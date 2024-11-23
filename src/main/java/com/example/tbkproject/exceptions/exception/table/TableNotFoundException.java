package com.example.tbkproject.exceptions.exception.table;

public class TableNotFoundException extends RuntimeException{
    public TableNotFoundException() {
        super("Table not found");
    }
}
