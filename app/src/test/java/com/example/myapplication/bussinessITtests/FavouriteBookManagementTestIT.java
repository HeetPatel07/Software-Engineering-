package com.example.myapplication.bussinessITtests;

import static org.junit.Assert.*;


import com.example.myapplication.Models.Book;


import com.example.myapplication.Models.User;
import com.example.myapplication.application.Services;

import com.example.myapplication.business.authentication.AuthenticatedUser;
import com.example.myapplication.business.management.AccountManagement;
import com.example.myapplication.business.management.AuthenticationManager;
import com.example.myapplication.business.management.BookManagement;
import com.example.myapplication.business.management.FavouriteBookManagement;
import com.example.myapplication.customException.BookNotFoundException;
import com.example.myapplication.customException.UserCreationException;


import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.io.File;
import java.io.IOException;

public class FavouriteBookManagementTestIT {

    private File tempDB;
    List<Book> books;

    FavouriteBookManagement favBookManagement;

    BookManagement bookManagement;

    @Before
    public void setup() {

        try {
            this.tempDB = TestUtils.copyDB();
        } catch (IOException e) {
            System.out.println("Error starting the test");
            fail();
        }
        favBookManagement = new FavouriteBookManagement(Services.getFavBooksDatabase());
        bookManagement = new BookManagement(Services.getBookDatabase());
    }

    private void getFavBooks(int userid) {
        try {
            books = favBookManagement.getFavBooks(userid);
        } catch (BookNotFoundException e) {
            System.out.println("Exception thrown");
        }
    }

    @Test
    public void getFavBooksTest() {

        assertThrows(BookNotFoundException.class, () -> {
            favBookManagement.getFavBooks(-1);
        });

        getFavBooks(1);

        assertFalse(books.isEmpty());

        getFavBooks(2);

        assertFalse(books.isEmpty());

        try {
            AccountManagement account = new AccountManagement(Services.getUserDatabase());
            account.createNewUser("Sample", "123123", "student", "address");

            AuthenticationManager authenticationManager = new AuthenticationManager(Services.getUserDatabase());

            authenticationManager.authenticateUser("Sample", "123123");

            User user = AuthenticatedUser.getInstance().getUser();

            assertNotNull(user);

            getFavBooks(user.getUserID());

            assertTrue(books.isEmpty());

            account.logoutUser();

        } catch (UserCreationException e) {
            System.out.println("Creating a new user failed");
        }
    }


    @Test
    public void addFavBookTest() {
        getFavBooks(1);
        books = bookManagement.viewBooks();

        Book booktoadd = books.get(books.size() - 1);
        try {
            favBookManagement.addFavBook(1, booktoadd);
        } catch (BookNotFoundException e) {

            System.out.println("Failed the test");
        }
        getFavBooks(1);

        boolean result = false;
        for (Book book : books) {
            if (book.getId() == booktoadd.getId()) {
                result = true;
                break;
            }
        }
        assertTrue(result);
    }

    @Test
    public void deleteFavBookTest() {

        getFavBooks(1);


        books = bookManagement.viewBooks();
        Book booktoadd = books.get(books.size() - 1);

        assertThrows(BookNotFoundException.class, () -> {
            favBookManagement.removeFavBook(-1, booktoadd.getId());
        });

        assertThrows(BookNotFoundException.class, () -> {
            favBookManagement.removeFavBook(1, -1);
        });

        assertFalse(books.isEmpty());
        Book booktoDelete = books.get(books.size() - 1);

        try {
            favBookManagement.addFavBook(1, booktoadd);
            favBookManagement.removeFavBook(1, booktoDelete.getId());
        } catch (BookNotFoundException e) {
            fail("Test for removing favourite book failed");
        }
    }
}