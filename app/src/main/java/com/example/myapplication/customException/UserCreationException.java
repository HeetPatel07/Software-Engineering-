package com.example.myapplication.customException;

public class UserCreationException extends Exception{

    public UserCreationException(String message) {
        super(message);
    }
}
