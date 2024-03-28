package com.example.myapplication.customException;

public class BadCourseException extends Exception {

    public BadCourseException() {
    }

    public BadCourseException(String message) {
        super(message);
    }
}
