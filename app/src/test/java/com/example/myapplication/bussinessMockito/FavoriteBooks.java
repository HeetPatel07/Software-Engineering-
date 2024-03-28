package com.example.myapplication.bussinessMockito;

import com.example.myapplication.Models.Book;
import com.example.myapplication.business.management.FavouriteBookManagement;
import com.example.myapplication.customException.BookCreationException;
import com.example.myapplication.customException.BookNotFoundException;
import com.example.myapplication.persistence.implementation.FavoriteBooksDatabaseImpl;
import com.example.myapplication.persistence.subinterfaces.FavoriteBooksDatabase;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class FavoriteBooks {

    private FavoriteBooksDatabase favoriteBooksDatabase;
    private FavouriteBookManagement favouriteBookManagement;

    @Before
    public void setUp() {
        // Mock the FavoriteBooksDatabase interface
        favoriteBooksDatabase = Mockito.mock(FavoriteBooksDatabaseImpl.class);
        // Initialize the FavouriteBookManagement with the mocked database
        favouriteBookManagement = new FavouriteBookManagement(favoriteBooksDatabase);
    }

    @Test
    public void addFavBook_invalidInput() {
        try {
            favouriteBookManagement.addFavBook(-1, null);
            fail("Expected BookNotFoundException");
        } catch (BookNotFoundException e) {
            assertNotNull(e.getMessage());
        }
    }



    @Test
    public void removeFavBook_invalidInput() {
        try {
            favouriteBookManagement.removeFavBook(-1, -1);
            fail("Expected BookNotFoundException");
        } catch (BookNotFoundException e) {
            assertNotNull(e.getMessage());
        }
    }

    @Test
    public void getFavBooks_success() {
        try {
            Book testBook1 = new Book(1, "Test Book 1", 9.99, "Description 1", 1.0, "Author 1", "New");
            Book testBook2 = new Book(2, "Test Book 2", 19.99, "Description 2", 2.0, "Author 2", "Used");
            when(favoriteBooksDatabase.getFavoriteBooks(anyInt())).thenReturn(Arrays.asList(testBook1, testBook2));

            List<Book> favBooks = favouriteBookManagement.getFavBooks(1);
            assertNotNull(favBooks);
            assertEquals(2, favBooks.size());
            verify(favoriteBooksDatabase, times(1)).getFavoriteBooks(1);
        } catch (BookNotFoundException e) {
            fail("Unexpected BookNotFoundException");
        }
    }

    @Test
    public void getFavBooks_invalidUserId() {
        try {
            favouriteBookManagement.getFavBooks(-1);
            fail("Expected BookNotFoundException");
        } catch (BookNotFoundException e) {
            assertNotNull(e.getMessage());
        }
    }

    @Test
    public void getFavBooks_emptyList() {
        try {
            when(favoriteBooksDatabase.getFavoriteBooks(anyInt())).thenReturn(Arrays.asList());

            List<Book> favBooks = favouriteBookManagement.getFavBooks(1);
            assertTrue(favBooks.isEmpty());
        } catch (BookNotFoundException e) {
            fail("Unexpected BookNotFoundException");
        }
    }

    @Test
    public void addFavBook_databaseConnectionFailure() {
        try {
            Book testBook = new Book(4, "DB Fail Book", 22.50, "Test description", 1.5, "Test Author", "Used");
            doThrow(new RuntimeException("Database connection failed")).when(favoriteBooksDatabase).addFavoriteBook(anyInt(), anyInt());

            favouriteBookManagement.addFavBook(1, testBook);
            fail("Expected RuntimeException was not thrown");
        } catch (RuntimeException e) {
            assertEquals("Database connection failed", e.getMessage());
        } catch (BookNotFoundException e) {
            fail("Unexpected BookNotFoundException");
        }catch (BookCreationException e){
            fail("Unexpected BookNotFoundException");
        }
    }

    @Test
    public void addFavBook_nullBook() {
        try {
            favouriteBookManagement.addFavBook(1, null);
            fail("Expected BookNotFoundException");
        } catch (BookNotFoundException e) {
            assertNotNull(e.getMessage());
        }
    }

    @Test
    public void addFavBook_databaseError() {
        try {
            Book testBook = new Book(3, "Error Book", 25.00, "Error Description", 1.0, "Error Author", "New");
            doThrow(new RuntimeException("Database connection failed")).when(favoriteBooksDatabase).addFavoriteBook(anyInt(), eq(testBook.getId()));

            favouriteBookManagement.addFavBook(1, testBook);
            fail("Expected RuntimeException was not thrown");
        } catch (RuntimeException e) {
            assertEquals("Database connection failed", e.getMessage());
        } catch (BookNotFoundException e) {
            fail("Unexpected BookNotFoundException");
        } catch (BookCreationException e){
            fail("Unexpected BookNotFoundException");
        }
    }
}
