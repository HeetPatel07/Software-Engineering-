package com.example.myapplication.bussinessMockito;

import com.example.myapplication.Models.Book;
import com.example.myapplication.customException.BookNotFoundException;
import com.example.myapplication.persistence.subinterfaces.BookDatabase;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BookManagement {

    @Mock
    private BookDatabase bookDatabase;

    private com.example.myapplication.business.management.BookManagement bookManagement;

    @Before
    public void setUp() {
        bookDatabase = Mockito.mock(BookDatabase.class);
        bookManagement = new com.example.myapplication.business.management.BookManagement(bookDatabase);
    }

    @Test
    public void testViewBooks() {
        // Mock book data
        List<Book> expectedBooks = new ArrayList<>();
        expectedBooks.add(new Book(1, "Book 1", 10.0, "Description 1", 1.0, "Author 1", "New"));
        expectedBooks.add(new Book(2, "Book 2", 20.0, "Description 2", 2.0, "Author 2", "Used"));
        when(bookDatabase.getBooks()).thenReturn(expectedBooks);

        // Call the method under test
        List<Book> result = bookManagement.viewBooks();

        // Verify that the correct list of books is returned
        assertEquals(expectedBooks, result);
    }

    @Test
    public void testFindBooksWithBookName() throws BookNotFoundException {
        // Mock book data
        String bookName = "Book";
        List<Book> expectedBooks = new ArrayList<>();
        expectedBooks.add(new Book(1, "Book 1", 10.0, "Description 1", 1.0, "Author 1", "New"));
        expectedBooks.add(new Book(2, "Book 2", 20.0, "Description 2", 2.0, "Author 2", "Used"));
        when(bookDatabase.findBooksWithBookName(anyString())).thenReturn(expectedBooks);

        // Call the method under test
        List<Book> result = bookManagement.findBooksWithBookName(bookName);

        // Verify that the correct list of books is returned
        assertEquals(expectedBooks, result);
    }



    @Test
    public void testFindBookWithID() throws BookNotFoundException {
        // Mock book data
        int bookId = 1;
        Book expectedBook = new Book(bookId, "Book 1", 10.0, "Description 1", 1.0, "Author 1", "New");
        when(bookDatabase.findBookWithID(bookId)).thenReturn(expectedBook);

        // Call the method under test
        Book result = bookManagement.findBookWithID(bookId);

        // Verify that the correct book is returned
        assertEquals(expectedBook, result);
    }


    @Test
    public void testSortByPrice() {
        // Mock book data
        List<Book> originalList = new ArrayList<>();
        originalList.add(new Book(1, "Book 1", 20.0, "Description 1", 1.0, "Author 1", "New"));
        originalList.add(new Book(2, "Book 2", 10.0, "Description 2", 2.0, "Author 2", "Used"));

        // Call the method under test
        List<Book> result = bookManagement.sortByPrice(originalList);

        // Verify that the books are sorted by price in ascending order
        assertEquals(10.0, result.get(0).getPrice(), 0.001); // Delta value added
        assertEquals(20.0, result.get(1).getPrice(), 0.001); // Delta value added
    }


}
