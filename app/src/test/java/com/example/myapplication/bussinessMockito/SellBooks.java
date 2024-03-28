package com.example.myapplication.bussinessMockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import com.example.myapplication.Models.Book;
import com.example.myapplication.Models.User;
import com.example.myapplication.business.management.SellBooksManagement;
import com.example.myapplication.customException.BookNotFoundException;
import com.example.myapplication.persistence.subinterfaces.BookDatabase;
import com.example.myapplication.persistence.subinterfaces.SellBooksDatabase;

public class SellBooks {

    private SellBooksManagement sellBooksManagement;
    private BookDatabase bookDatabase;
    private SellBooksDatabase sellBooksDatabase;

    @Before
    public void setUp() {
        bookDatabase = mock(BookDatabase.class);
        sellBooksDatabase = mock(SellBooksDatabase.class);
        User user = new User("username", 11, "password", "Student", "address"); // Mock user
        sellBooksManagement = new SellBooksManagement(bookDatabase, sellBooksDatabase, user);
    }

    @Test
    public void testBookExists() throws BookNotFoundException {


        // Create a book
        Book book = new Book(1, "Test Book", 9.99, "Description", 1.0, "Author", "New");

        // Mock bookDatabase to return the book when findBookWithID is called
        when(bookDatabase.findBookWithID(1)).thenReturn(book);

        // Call the bookExists method
        String result = sellBooksManagement.bookExists(1, 9.99f, "New");

        // Verify that the result is the book name
        assertEquals("Test Book", result);
    }

    @Test
    public void testGetUsedBooksForSale() {
        // Mock list of books
        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book(1, "Test Book", 9.99, "Description", 1.0, "Author", "New"));
        when(sellBooksDatabase.getBooksForSale(1)).thenReturn(bookList);

        // Call the method under test
        List<Book> result = sellBooksManagement.getUsedBooksForSale(1);

        // Verify that the correct list of books is returned
        assertEquals(bookList, result);

        // Verify that getBooksForSale method was called with correct parameters
        verify(sellBooksDatabase, times(1)).getBooksForSale(1);
    }

    @Test
    public void testBookDoesNotExist() throws BookNotFoundException {
        // Assuming 'bookDatabase' is an instance of BookDatabase
        // Mock bookDatabase to return Optional.empty()
        when(bookDatabase.findBookWithID(1)).thenReturn(null);

        // Call the method under test
        String result = sellBooksManagement.bookExists(1, 9.99f, "New");

        // Verify that null is returned since book does not exist
        assertEquals(null, result);

        // Verify that addSaleBook method was not called since book does not exist
        verify(sellBooksDatabase, never()).addSaleBook(anyInt(), anyInt(), anyString(), anyFloat());
    }
}
