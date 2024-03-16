package com.example.myapplication.business.management;

import com.example.myapplication.Models.Book;

import java.util.List;

import com.example.myapplication.customException.CheckoutException;

import java.util.ArrayList;


public class CheckoutManagement {
    private static List<Book> books = new ArrayList<>();
    private static final CheckoutManagement shoppingCart = new CheckoutManagement();

    public CheckoutManagement() {
    } //no class will be able to call this

    public static CheckoutManagement getInstance() {
        return shoppingCart;
    }

    public List<Book> getCheckoutBooks() {
        return books;
    }

    public void buyBook(Book book) throws CheckoutException {

        if (addBookToList(book)) {
            addBookToList(book);
            throw new CheckoutException("Book " + book.getBookName() + " added to the cart");
        } else {
            throw new CheckoutException("Book" + book.getBookName() + "was not added to the cart cause it is already in your cart");
        }
    }

    private boolean addBookToList(Book book) {
        try {
            // Check if the book already exists in the cart
            for (Book existingBook : books) {
                if (existingBook.getId() == book.getId()) {
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
            boolean isRemoved = books.removeIf(existingBook -> existingBook.getId() == book.getId());
            if (isRemoved)
                return true; // Book successfully removed

        } catch (RuntimeException e) {
            System.out.println("Error in removing " + book.getBookName() + " from the checkout page: " + e.getMessage());
            return false; // Unable to remove book due to error
        }

        return false;
    }

    public boolean buyBooks(Book book) {
       // try{}catch(){}
        return false;
    }

    public boolean isEmpty() {
        return  books.isEmpty();
    }
}
