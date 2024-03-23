package com.example.myapplication.customException;

public class UserCreationException extends IllegalArgumentException{

    public UserCreationException(String message) {
        super(message);
    }
}
