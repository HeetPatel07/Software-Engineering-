package com.example.myapplication.persistence.subinterfaces;

import com.example.myapplication.Models.Book;
import com.example.myapplication.persistence.Database;

import java.util.List;

public interface SellBooksDatabase extends Database {

    List<Book> getBooksForSale(int userId);

    boolean deleteSaleBook(int userId, int bookId);

    boolean addSaleBook(int userId, int bookId);

}
