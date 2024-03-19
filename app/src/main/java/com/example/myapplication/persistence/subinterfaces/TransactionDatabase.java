package com.example.myapplication.persistence.subinterfaces;

import com.example.myapplication.Models.Book;
import com.example.myapplication.customException.CheckoutException;
import com.example.myapplication.persistence.Database;
import com.example.myapplication.Models.User;
import com.example.myapplication.Models.Transaction;

import java.util.List;

public interface TransactionDatabase extends Database {
    List<Transaction> getPurchaseHistory(User user) throws CheckoutException;

    boolean deleteBookForSale(User user,Book book);

    boolean addSaleBook(int userId, int bookId, String bookCondition, double price);

}
