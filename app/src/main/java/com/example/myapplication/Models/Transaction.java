package com.example.myapplication.Models;

import com.example.myapplication.customException.CheckoutException;

public class Transaction {

    private String deliveredTo;
    Book book;
    private double price;
//t.book_id, t.amount, t.address,b.bookname, b.author_name
    public Transaction(String deliveredTo,Double amount,Book book){

        this.deliveredTo=deliveredTo;
        this.price=amount;
        this.book=book;

    }

    public String getDeliveredTo(){
        return deliveredTo;
    }
    public Book getBook(){
        return book;
    }
    public double getAmountPaid(){
        return price;
    }

    public Book getSoldBook(){
        return book;
    }

    public void addTransaction(User user,Book book) throws CheckoutException {

    }



}
