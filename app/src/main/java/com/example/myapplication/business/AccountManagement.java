package com.example.myapplication.business;

import com.example.myapplication.Models.User;
import com.example.myapplication.persistence.Database;

public class AccountManagement {

    private final Database dummyDatabase;
    public AccountManagement(Database dummyDatabase) {
        this.dummyDatabase =  dummyDatabase;
    }

    public boolean createNewUser(String userName, String password, String type, String address) {

        // Check if the username length is not greater than 3
        if(userName == null || userName.length() <= 3) {
            throw new IllegalArgumentException("Username must be longer than 3 characters.");
        }

        // Check if the password length is not greater than 4
        if(password == null || password.length() <= 4) {
            throw new IllegalArgumentException("Password must be longer than 4 characters.");
        }

        // If both username and password meet the criteria, attempt to add the user to the database\
        return dummyDatabase.addUser(userName, password, type, address);
    }


    //don't need this method here
    public boolean setNewPassword(String newPassword){
        User authenticatedUser = AuthenticatedUser.getInstance().getUser();

        if(authenticatedUser == null){
            System.out.println("User is not yet authenticated");
            return false;
        }

        if(newPassword.length() > 4)
            return authenticatedUser.setPassword(authenticatedUser.getPassword(),newPassword);

        System.out.println("Password needs to be more than 4 letters");
        return false;
    }
}
