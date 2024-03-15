package com.example.myapplication.persistence.exceptions;

import androidx.annotation.Nullable;

public class CheckoutException extends Exception{
    public CheckoutException() {
    }

    public CheckoutException(String message) {
        super(message);
    }

    @Nullable
    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
