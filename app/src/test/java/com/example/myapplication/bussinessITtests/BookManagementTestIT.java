package com.example.myapplication.bussinessITtests;

import com.example.myapplication.application.Services;

import com.example.myapplication.business.management.BookManagement;

import com.example.myapplication.Models.Book;
import com.example.myapplication.customException.BookNotFoundException;
import com.example.myapplication.persistence.subinterfaces.BookDatabase;
import com.example.myapplication.persistence.subinterfaces.SellBooksDatabase;


import org.junit.*;
import org.junit.Test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class BookManagementTestIT {

    private BookDatabase bookDatabase;
    private BookManagement bookManagement;
    SellBooksDatabase addForSale;
    private File tempDB;
    List<Book> list;
    Book book;

    @Before
    public void createTest() {

        System.out.println("Starting integration test for AccessRecipes");
        try {
            this.tempDB = TestUtils.copyDB();
        } catch (IOException e) {
            System.out.println("Error starting the test");
            fail();
        }

        bookDatabase = Services.getBookDatabase();
        addForSale = Services.getSellBooksDatabase();
        bookManagement = new BookManagement(bookDatabase);
    }


    /*Simple test of retrieving all the books from the database*/
    @Test
    public void testingRetrievalOfBooks() {
        list = bookManagement.viewBooks();
        assertTrue(!list.isEmpty());
    }

    public List<Book> findName(String name) {
        List<Book> result = null;

        try {
            result = bookManagement.findBooksWithBookName(name);
        } catch (BookNotFoundException e) {
            System.out.println("Bad error thrown from Database");
        }


        return result;
    }

    @Test
    public void findBooksWithBookName() {

        list = findName("WrongBook");
        assertTrue(list.size() == 0);

        list = findName("moby");//this should return 2 books
        assertTrue(list.size() == 2);

        list = findName("Moby"); // checking the case sensitivity
        assertTrue(list.size() == 2);

        list = findName("bio");
        assertTrue(list.size() == 1);

        list = findName("BIO");
        assertTrue(list.size() == 1);

        book = list.remove(0);

        addForSale.addSaleBook(1, book.getId(), "TEST", book.getPrice());

        list = findName("BIO");
        assertTrue(list.size() == 2);
    }

    @Test
    public void findBookID() {

        book = bookManagement.findBookWithID(-1);
        assertNull(book);

        book = bookManagement.findBookWithID(10000);
        assertNull(book);

        book = bookManagement.findBookWithID(1);
        assertNotNull(book);
    }
}
