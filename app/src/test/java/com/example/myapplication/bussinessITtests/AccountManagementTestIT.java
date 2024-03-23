package com.example.myapplication.bussinessITtests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import com.example.myapplication.application.Services;
import com.example.myapplication.business.management.AccountManagement;
import com.example.myapplication.business.management.AuthenticationManager;
import com.example.myapplication.customException.UserCreationException;
import com.example.myapplication.persistence.implementation.UserDatabaseImpl;
import com.example.myapplication.persistence.stub.DummyDatabase;
import com.example.myapplication.persistence.utils.DBHelper;

import org.junit.Before;
import org.junit.Test;



public class AccountManagementTestIT {

    static AccountManagement accountManagement;
    static AuthenticationManager authenticationManager;

    static UserDatabaseImpl db;
    protected String dbpath;


    static boolean flag=false;     //set up flag
    @Before
    public  void  setUpTest() {

        AccountManagement accountManagement1 = new AccountManagement(Services.getUserDatabase());
        try {

            accountManagement1.createNewUser("tom","1234","User","R3T");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        if(!flag) {
            DummyDatabase dummyDatabase = (DummyDatabase) DummyDatabase.getInstance();
            accountManagement = new AccountManagement(dummyDatabase);
            authenticationManager = new AuthenticationManager(dummyDatabase);
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
        assertThrows(UserCreationException.class, ()->
                accountManagement.createNewUser(userName1,password1,"Professor",address1));

        String userName = "Sample";
        String password = "12345";
        String address = "testAddress";
//        assertTrue(accountManagement.createNewUser(userName,password,"Student",address));

    }


    @Test
    public void testValidSetNewPassword() throws UserCreationException {
        String userName = "TastyFood";
        String password = "original";
        String address = "testAddress";
        accountManagement.createNewUser(userName,password,"Student",address); //this adds the user to the database
        authenticationManager.authenticateUser(userName,password);    //this authenticates and initialises the singleton user

        password= "newPassword";
        boolean result = accountManagement.setNewPassword(password);
        assertFalse(result);


        password = "123";
        String finalPassword = password;
        assertThrows(IllegalArgumentException.class, ()->
                accountManagement.setNewPassword(finalPassword));


    }
}

