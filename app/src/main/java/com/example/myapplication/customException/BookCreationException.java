package com.example.myapplication.customException;

public class BookCreationException extends Exception{
    public BookCreationException(String message) {
        super(message);
    }
}
