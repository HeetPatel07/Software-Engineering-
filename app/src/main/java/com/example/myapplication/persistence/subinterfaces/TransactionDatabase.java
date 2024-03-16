package com.example.myapplication.persistence.subinterfaces;

import com.example.myapplication.Models.Book;
import com.example.myapplication.persistence.Database;
import com.example.myapplication.Models.User;
import com.example.myapplication.Models.Book;

import java.util.List;

public interface TransactionDatabase extends Database {
    List<Book> getPurchaseHistory(User user);

    boolean deleteBookForSale(User user,Book book);

    boolean addSaleBook(int userId, int bookId, String bookCondition, double price);

}
