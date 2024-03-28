package com.example.myapplication.customException;

public class BadRatingException extends Exception {

    public BadRatingException() {
    }

    public BadRatingException(String message) {
        super(message);
    }
}
