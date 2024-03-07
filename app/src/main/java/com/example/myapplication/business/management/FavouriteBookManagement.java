package com.example.myapplication.business.management;

import com.example.myapplication.Models.Book;

import java.util.List;

import com.example.myapplication.business.authentication.AuthenticatedUser;
import com.example.myapplication.persistence.subinterfaces.FavoriteBooksDatabase;

import java.util.Optional;


public class FavouriteBookManagement {

    private FavoriteBooksDatabase favouriteBookDatabase;

    public FavouriteBookManagement(FavoriteBooksDatabase favDatabase) {

        this.favouriteBookDatabase = favDatabase;

    }

    public void addFavBook(int userId, Book book) {

        if (userId < 0 || book ==null)
            throw new IllegalArgumentException("The userId or bookId is invalid for this action");
        try {
            favouriteBookDatabase.addFavoriteBook(userId, book.getId());
           // AuthenticatedUser.getInstance().getUser().addForSale(usedBook.addUsedBook(price, condition));
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }
    }

    public void removeFavBook(int userId, int bookId) {
        if (userId < 0 || bookId < 0)
            throw new IllegalArgumentException("The userId or bookId is invalid for this action");

        try {
            favouriteBookDatabase.deleteFavoriteBook(userId, bookId);
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }

    }

    public List<Book> getFavBooks(int userId) {
        if (userId < 0) throw new IllegalArgumentException("The userId is invalid for this action");
        return favouriteBookDatabase.getFavoriteBooks(userId);
    }
}
