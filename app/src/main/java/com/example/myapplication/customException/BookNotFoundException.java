package com.example.myapplication.customException;

public class BookNotFoundException extends Exception{
    public BookNotFoundException(String message) {
        super(message);
    }
}