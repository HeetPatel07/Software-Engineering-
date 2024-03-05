package com.example.myapplication.persistence.implementation;

import com.example.myapplication.Models.Book;
import com.example.myapplication.persistence.subinterfaces.FavoriteBooksDatabase;

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
public class FavoriteBooksDatabaseImpl implements FavoriteBooksDatabase {
    private String dbpath;
    public FavoriteBooksDatabaseImpl(String dbpath){
        this.dbpath = dbpath;
    }
    @Override
    public List<Book> getFavoriteBooks(int userId) {
        return null;
    }

    @Override
    public void addFavoriteBook(int userId, int bookId) {

    }

    @Override
    public void deleteFavoriteBook(int userId, int bookId) {

    }
}
