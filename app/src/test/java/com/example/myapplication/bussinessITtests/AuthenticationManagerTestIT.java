package com.example.myapplication.bussinessITtests;

import com.example.myapplication.Models.User;
import com.example.myapplication.application.Services;
import com.example.myapplication.business.authentication.AuthenticatedUser;
import com.example.myapplication.business.management.AccountManagement;
import com.example.myapplication.business.management.AuthenticationManager;
import com.example.myapplication.business.management.BookManagement;

import com.example.myapplication.Models.Book;
import com.example.myapplication.business.management.CheckoutManagement;
import com.example.myapplication.customException.BookNotFoundException;
import com.example.myapplication.customException.UserCreationException;
import com.example.myapplication.persistence.subinterfaces.BookDatabase;
import com.example.myapplication.persistence.subinterfaces.SellBooksDatabase;
import com.example.myapplication.persistence.subinterfaces.UserDatabase;


import org.junit.*;
import org.junit.Test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class AuthenticationManagerTestIT {

    UserDatabase userDB;

    AuthenticationManager authenticateManager;
    private File tempDB;
    
    @Before
    public void setup() {

        try {
            this.tempDB = TestUtils.copyDB();
        } catch (IOException e) {
            System.out.println("Error starting the test");
            fail();
        }
        userDB= Services.getUserDatabase();
        authenticateManager= new AuthenticationManager(userDB);
    }

    @Test
    public void authenticateTest(){
        assertFalse(authenticateManager.authenticateUser("Test","password"));

        assertTrue(authenticateManager.authenticateUser("userone","123123"));

        AccountManagement management = new AccountManagement(Services.getUserDatabase());

        try {
            management.createNewUser("Test", "password", "TestingType", "Integration Test");
        }catch (UserCreationException e){
            System.out.println(e.getMessage());
            fail();
        }
        assertTrue(authenticateManager.authenticateUser("Test","password"));
    }

}