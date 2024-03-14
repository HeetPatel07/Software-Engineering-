package com.example.myapplication.persistence.exceptions;

public class BookCreationException extends Exception{
    public BookCreationException() {
    }

    public BookCreationException(String message) {
        super(message);
    }
}
