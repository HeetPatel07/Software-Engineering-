package com.example.myapplication.persistence.subinterfaces;

import com.example.myapplication.Models.Book;
import com.example.myapplication.customException.BookCreationException;
import com.example.myapplication.customException.BookNotFoundException;
import com.example.myapplication.persistence.Database;

import java.util.List;

public interface FavoriteBooksDatabase extends Database {

    List<Book> getFavoriteBooks(int userId) throws BookNotFoundException;

    void addFavoriteBook(int userId, int bookId) throws BookCreationException;

    void deleteFavoriteBook(int userId, int bookId) throws BookNotFoundException;
}