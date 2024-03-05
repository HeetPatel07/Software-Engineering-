package com.example.myapplication.business;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import com.example.myapplication.business.management.AuthenticationManager;
import com.example.myapplication.persistence.DummyDatabase;

public class AccountmanagementTest
{
    static AccountManagement accountManagement;
    static AuthenticationManager classtoauthenticate;
    static DummyDatabase dummyDatabase;

    static boolean flag=false;     //set up flag


    @Before
    public  void  setUpTest() {
        if(!flag) {
            dummyDatabase = (DummyDatabase) DummyDatabase.getInstance();
            accountManagement = new AccountManagement(dummyDatabase);
            classtoauthenticate= new AuthenticationManager(dummyDatabase);
            flag=true;
        }else{
            System.out.println("The setup for Account Management is already done ");
        }
    }

    @Test
    public  void testCreateNewUserValidInput()
    {

        String userName1 = "yyy";   //invalid input for username
        String password1 = "nil3";  //invalid input for password
        String address1 = "testAddress";
        boolean result = accountManagement.createNewUser(userName1,password1,address1);
        assertFalse(result);

        String userName = "Sample";
        String password = "12345";
        String address = "testAddress";
        result = accountManagement.createNewUser(userName,password,address);
        assertTrue(result);

    }


    @Test
    public void testValidSetNewPassword()
    {
        String userName = "TastyFood";
        String password = "original";
        String address = "testAddress";
        accountManagement.createNewUser(userName,password,address); //this adds the user to the database
        classtoauthenticate.authenticateUser(userName,password);    //this authenticates and initialises the singleton user

        password= "newPassword";
        boolean result = accountManagement.setNewPassword(password);
        assertTrue(result);


        password = "123";
        result = accountManagement.setNewPassword(password);
        assertFalse(result);


    }
}

