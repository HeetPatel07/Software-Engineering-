package com.example.myapplication.persistence.exceptions;

public class UserCreationException extends Exception{
    public UserCreationException() {
        super();
    }

    public UserCreationException(String message) {
        super(message);
    }
}
