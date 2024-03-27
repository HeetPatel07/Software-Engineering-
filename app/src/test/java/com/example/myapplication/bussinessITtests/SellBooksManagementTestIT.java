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
import com.example.myapplication.business.management.SellBooksManagement;
import com.example.myapplication.customException.BookNotFoundException;
import com.example.myapplication.customException.UserCreationException;


import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.io.File;
import java.io.IOException;

public class SellBooksManagementTestIT {

    private File tempDB;

    SellBooksManagement sellBooksManagement;
    AuthenticationManager authenticationManager;
    User user ;

    @Before
    public void setup() {

        try {
            this.tempDB = TestUtils.copyDB();
        } catch (IOException e) {
            System.out.println("Error starting the test");
            fail();
        }
        authenticationManager = new AuthenticationManager(Services.getUserDatabase());
        authenticationManager.authenticateUser("usersix", "password000");
        user = AuthenticatedUser.getInstance().getUser();
        sellBooksManagement = new SellBooksManagement(Services.getBookDatabase(), Services.getSellBooksDatabase(), user);
    }

    @Test
    public void getUsedBooksForSaleTest() {
        List<Book> result;
        result = sellBooksManagement.getUsedBooksForSale(-1);
        assertTrue(result.isEmpty());

        result= sellBooksManagement.getUsedBooksForSale(user.getUserID());
        assertFalse(result.isEmpty());

         sellBooksManagement.bookExists(user.getUserID(), 20, "used");

        List <Book>result2 = sellBooksManagement.getUsedBooksForSale(user.getUserID());

        assertTrue(result2.size()>result.size());
    }

    @Test
    public void addBookTest() {
        String bookName = sellBooksManagement.bookExists(1000, 2000, "new");
        assertNull(bookName);
        bookName = sellBooksManagement.bookExists(user.getUserID(), 20, "used");
        assertNotNull(bookName);
    }
}
