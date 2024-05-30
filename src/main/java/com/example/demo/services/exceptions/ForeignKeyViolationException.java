package com.example.demo.services.exceptions;

public class ForeignKeyViolationException extends IllegalArgumentException {

    public ForeignKeyViolationException(String message) {
        super(message);
    }

}
