package com.example.myapplication.business;

import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;

import com.example.myapplication.business.management.AuthenticationManager;
import com.example.myapplication.persistence.stub.DummyDatabase;


public class AuthenticationManagerTest{
    private AuthenticationManager authenticationManager;
    DummyDatabase database = (DummyDatabase) DummyDatabase.getInstance();
    @Before

    public void setUp()
    {
        authenticationManager = new AuthenticationManager(database);

    }

    @Test
    public void testAuthenticateUser_UserExists_PasswordMatches() {
        System.out.println("Staring testAuthenticateUser_UserExists_PasswordMatches");
        String username = "testUser";
        String password = "testPassword";
        database.addUser(username,password,"user", "address");


        boolean result = authenticationManager.authenticateUser(username, password);

        assertTrue(result);
        System.out.println("Finished testAuthenticateUser_UserExists_PasswordMatches");

    }
    @Test
    public void testAuthenticateUser_UserExists_PasswordNotMatches() {
        System.out.println("Starting testAuthenticateUser_UserExists_PasswordNotMatches");
        String username = "testUser1";
        String password = "testPassword";
        String wrongPassword = "wrongPassword";


        database.addUser(username,password,"user", "address");


        boolean result = authenticationManager.authenticateUser(username, wrongPassword);

        assertFalse(result);
        System.out.println("finished testAuthenticateUser_UserExists_PasswordNotMatches");

    }

}