package com.example.myapplication.business.management;

import com.example.myapplication.Models.Book;

import java.sql.SQLException;
import java.util.List;

import com.example.myapplication.Models.Transaction;
import com.example.myapplication.business.authentication.AuthenticatedUser;
import com.example.myapplication.customException.CheckoutException;
import com.example.myapplication.persistence.subinterfaces.FavoriteBooksDatabase;
import com.example.myapplication.persistence.subinterfaces.TransactionDatabase;

import java.util.ArrayList;


public class CheckoutManagement {
    private static List<Book> books = new ArrayList<>();

    private TransactionDatabase purchaseHistory;
    private static final CheckoutManagement shoppingCart = null;

    public CheckoutManagement(TransactionDatabase DB) {
        purchaseHistory = DB;
    } //no class will be able to call this

    public static CheckoutManagement getInstance() {
        return shoppingCart;
    }

    public List<Book> getCheckoutBooks() {
        return books;
    }

    public boolean buyBook(Book book) {
        if(book == null) return false;
        return addBookToList(book);
    }


    private boolean addBookToList(Book book) {
        try {
            // Check if the book already exists in the cart
            for (Book existingBook : books) {
                if (existingBook.equals(book)) {
                    // Book already exists in the cart, do not add
                    System.out.println("Book " + book.getBookName() + " already exists in the checkout page");
                    return false;
                }
            }
            // Book does not exist in the cart, add it
            books.add(book);
        } catch (Exception e) {
            System.out.println("Error in adding " + book.getBookName() + " to the checkout page: " + e.getMessage());
        }
        return true;
    }


    public boolean removeBook(Book book) {
        try {
            // Check if the book exists in the cart
            boolean isRemoved = books.removeIf(existingBook -> existingBook.equals(book));
            if (isRemoved)  return true; // Book successfully removed
        } catch (RuntimeException e) {
            System.out.println("Error in removing " + book.getBookName() + " from the checkout page: " + e.getMessage());
            return false; // Unable to remove book due to error
        }
        return false;
    }


    public List<Transaction> pastPurchases() throws CheckoutException {
        List<Transaction> result = null;

        try {
            result = purchaseHistory.getPurchaseHistory(AuthenticatedUser.getInstance().getUser());
        } catch (CheckoutException e) {
            throw new CheckoutException(e.getMessage());
        }

        return result;
    }

    public void finishTransaction() throws CheckoutException {

        if (books.isEmpty()) {
            throw new CheckoutException("No books in you cart");
        }
        //checking out all the books in the cart
        for (Book book : books)
            purchaseHistory.deleteBookForSale(AuthenticatedUser.getInstance().getUser(), book);
        books.clear();
    }


    public boolean isEmpty() {
        return books.isEmpty();
    }
}
