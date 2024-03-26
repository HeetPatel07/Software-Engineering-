package com.example.myapplication.bussinessITtests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.*;

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

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;


public class AccountManagementTestIT {

    UserDatabase userDB;
    AccountManagement accountManagement;

    AuthenticationManager authenticateManager;
    private File tempDB;

    @Before
    public void setup() {

        System.out.println("Starting integration test for AccessRecipes");
        try {
            this.tempDB = TestUtils.copyDB();
        } catch (IOException e) {
            System.out.println("Error starting the test");
            fail();
        }
        userDB = Services.getUserDatabase();
        authenticateManager = new AuthenticationManager(userDB);
        accountManagement = new AccountManagement(Services.getUserDatabase());
    }


    private boolean creatingAccount(String userName, String password, String type, String address) {
        try {
            return accountManagement.createNewUser(userName, password, type, address);
        } catch (UserCreationException e) {
            System.out.println("Error in creating the new user");
            return false;
        }

    }

    @Test
    public void testingCreation() {
        assertFalse(creatingAccount(null, null, null, null));
        assertTrue(creatingAccount("Testing", "password", "TestType", "address"));

        //testing whether the new user is added in the database
        assertTrue(authenticateManager.authenticateUser("Testing", "password"));

        User user = AuthenticatedUser.getInstance().getUser();

        assertEquals(user.getUsername(), "Testing");
        assertEquals(user.getPassword(), "password");
        assertEquals(user.getType(), "TestType");
        assertEquals(user.getAddress(), "address");
    }

    @Test
    public void testLoggingOut() {

        accountManagement.logoutUser();
        assertNull(AuthenticatedUser.getInstance().getUser());
        accountManagement.logoutUser();
        accountManagement.logoutUser();
        assertNull(AuthenticatedUser.getInstance().getUser());

        authenticateManager.authenticateUser("userone", "123123");
        assertNotNull(AuthenticatedUser.getInstance().getUser());
        accountManagement.logoutUser();
    }

    @Test
    public void setNewPasswordTest() {
        accountManagement.logoutUser();

        assertThrows(UserCreationException.class, () -> {
            accountManagement.setNewPassword(null);
        });

        authenticateManager.authenticateUser("userone", "123123");

        assertNotNull(AuthenticatedUser.getInstance().getUser());

        assertThrows(UserCreationException.class, () -> {
            accountManagement.setNewPassword(null);
        });
    }



    @After
    public void finish() {
        if (AuthenticatedUser.getInstance().getUser() != null)
            accountManagement.logoutUser();
    }


}