package com.example.myapplication.persistence.subinterfaces;

import com.example.myapplication.Models.Book;
import com.example.myapplication.persistence.Database;

import java.util.List;

public interface FavoriteBooksDatabase extends Database {

    List<Book> getFavoriteBooks(int userId);
    boolean addFavoriteBook(int userId, int bookId);
    boolean deleteFavoriteBook(int userId, int bookId);
}