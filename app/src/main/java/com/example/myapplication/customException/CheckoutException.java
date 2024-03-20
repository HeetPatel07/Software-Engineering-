package com.example.myapplication.customException;

import androidx.annotation.Nullable;

public class CheckoutException extends Exception{
    public CheckoutException(String message) {
        super(message);
    }
    @Nullable
    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
