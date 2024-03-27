package com.example.myapplication.business.management;

import com.example.myapplication.Models.Book;

import java.util.List;

import com.example.myapplication.customException.BookCreationException;
import com.example.myapplication.customException.BookNotFoundException;
import com.example.myapplication.persistence.subinterfaces.FavoriteBooksDatabase;


public class FavouriteBookManagement {

    private FavoriteBooksDatabase favouriteBookDatabase;

    public FavouriteBookManagement(FavoriteBooksDatabase favDatabase) {
        this.favouriteBookDatabase = favDatabase;
    }

    public List<Book> getFavBooks(int userId) throws BookNotFoundException {
        if (userId < 0) throw new BookNotFoundException("The userId is invalid for this action");
        return favouriteBookDatabase.getFavoriteBooks(userId);
    }

    public void addFavBook(int userId, Book book) throws BookNotFoundException {

        if (userId < 0 || book == null)
            throw new BookNotFoundException("The userId or bookId is invalid for this action");
        try {
            favouriteBookDatabase.addFavoriteBook(userId, book.getId());

        } catch (BookCreationException e) {
            System.out.println(e);
        }
    }

    public boolean removeFavBook(int userId, int bookId) throws BookNotFoundException {
        if (userId < 0 || bookId < 0)
            throw new BookNotFoundException("The userId or bookId is invalid for this action");

        try {
            favouriteBookDatabase.deleteFavoriteBook(userId, bookId);
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }

        return false;
    }
}
