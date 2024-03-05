package com.example.myapplication.persistence.implementation;

import com.example.myapplication.Models.Book;
import com.example.myapplication.persistence.subinterfaces.SellBooksDatabase;

import java.util.List;

/**
 * try {
 *         Connection connection = BookDatabase.super.getConnection();
 *         System.out.println(connection);
 *         }
 *         catch (SQLException e){
 *         }

 * For add, update, delete methods use transactions and write the queries
 */
public class SellBooksDatabaseImpl implements SellBooksDatabase {
    private String dbpath;
    public SellBooksDatabaseImpl(String dbpath){
        this.dbpath = dbpath;
    }
    @Override
    public List<Book> getBooksForSale(int userId) {
        return null;
    }

    @Override
    public boolean deleteSaleBook(int userId, int bookId) {
        return false;
    }

    @Override
    public boolean addSaleBook(int userId, int bookId) {
        return false;
    }
}
