package com.example.demo.services.exceptions;

public class ObjectExistException extends IllegalArgumentException {

    public ObjectExistException(String message) {
        super(message);
    }

}
