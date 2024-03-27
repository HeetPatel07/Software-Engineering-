package com.example.myapplication.bussinessITtests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.*;

import com.example.myapplication.Models.User;
import com.example.myapplication.application.Services;
import com.example.myapplication.business.authentication.AuthenticatedUser;
import com.example.myapplication.business.management.AccountManagement;
import com.example.myapplication.business.management.AuthenticationManager;
import com.example.myapplication.customException.UserCreationException;
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

        authenticateManager.authenticateUser("userfour", "123123");
        assertNotNull(AuthenticatedUser.getInstance().getUser());
        accountManagement.logoutUser();
    }

    @Test
    public void setNewPasswordTest() {
        accountManagement.logoutUser();

        assertThrows(UserCreationException.class, () -> {
            accountManagement.setNewPassword(null);
        });

        authenticateManager.authenticateUser("usertwo", "123123");

        assertNotNull(AuthenticatedUser.getInstance().getUser());

        assertThrows(UserCreationException.class, () -> {
            accountManagement.setNewPassword(null);

        });
        try {
            assertTrue(accountManagement.setNewPassword("111000"));
        } catch (UserCreationException e) {
            System.out.println(e.getMessage());
            fail();
        }
        assertTrue(AuthenticatedUser.getInstance().getUser().getPassword().equalsIgnoreCase("111000"));

        accountManagement.logoutUser();

        authenticateManager.authenticateUser("usertwo", "111000");

        assertTrue(AuthenticatedUser.getInstance().getUser().getUsername().equals("usertwo"));
    }

    private boolean setUsername(String name) {
        try {
            return accountManagement.setNewUserName(name);
        } catch (UserCreationException e) {
            return false;
        }
    }

    @Test
    public void setUserNameTest() {
        accountManagement.logoutUser();
        assertFalse(setUsername("Testing"));

        //logging in the user
        authenticateManager.authenticateUser("userone", "123123");

        assertFalse(setUsername(null));
        assertFalse(setUsername("asd"));//the user name

        assertTrue(setUsername("testing"));
        assertTrue(AuthenticatedUser.getInstance().getUser().getUsername().equals("testing"));

        assertFalse(setUsername("a"));
        assertTrue(setUsername("test2"));
        assertTrue(AuthenticatedUser.getInstance().getUser().getUsername().equals("test2"));


        accountManagement.logoutUser();

        authenticateManager.authenticateUser("test2", "123123");

        assertTrue(AuthenticatedUser.getInstance().getUser().getUsername().equals("test2"));

    }

    private boolean setAddress(String address) {

        try {
            return accountManagement.setNewUserAddress(address);
        } catch (UserCreationException e) {
            return false;
        }
    }

    @Test
    public void setNewUserAddressTest() {
        accountManagement.logoutUser();
        assertFalse(setAddress("address"));

        authenticateManager.authenticateUser("userthree", "123123");

        assertFalse(setAddress(null));
        assertTrue(setAddress("."));

        assertTrue(setAddress("testing address"));
        assertTrue(AuthenticatedUser.getInstance().getUser().getAddress().equals("testing address"));


    }


    @After
    public void finish() {
        if (AuthenticatedUser.getInstance().getUser() != null)
            accountManagement.logoutUser();
    }


}