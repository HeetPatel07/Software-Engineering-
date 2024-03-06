package com.example.myapplication.business;

import com.example.myapplication.Models.User;
import com.example.myapplication.persistence.Database;

public class AccountManagement {

    private final Database dummyDatabase;
    public AccountManagement(Database dummyDatabase) {
        this.dummyDatabase =  dummyDatabase;
    }

    public boolean createNewUser(String userName, String password, String type, String address){

        if(userName.length() > 3 && password.length() >4)
            return dummyDatabase.addUser(userName, password, type, address);
        return false;
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
