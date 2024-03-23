package com.example.myapplication.Models;

import com.example.myapplication.customException.CheckoutException;

public class Transaction {

    private String deliveredTo;
    Book book;
    private double price;

    public Transaction(String deliveredTo, Double amount, Book book) {

        this.deliveredTo = deliveredTo;
        this.price = amount;
        this.book = book;

    }

    public String getDeliveredTo() {
        return deliveredTo;
    }

    public Book getBook() {
        return book;
    }

    public double getAmountPaid() {
        return price;
    }

}
