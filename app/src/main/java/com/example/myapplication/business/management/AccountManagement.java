package com.example.myapplication.business.management;

import com.example.myapplication.Models.User;
import com.example.myapplication.business.authentication.AuthenticatedUser;
import com.example.myapplication.customException.UserCreationException;
import com.example.myapplication.persistence.subinterfaces.UserDatabase;

public class AccountManagement {

    private final UserDatabase database;

    public AccountManagement(UserDatabase database) {
        this.database = database;
    }

    public boolean createNewUser(String userName, String password, String type, String address) throws UserCreationException {

        // Check if the username length is not greater than 3
        if (userName == null || userName.length() <= 3) {
            throw new UserCreationException("Username must be longer than 3 characters.");
        }

        // Check if the password length is not greater than 4
        if (password == null || password.length() <= 4) {
            throw new UserCreationException("Password must be longer than 4 characters.");
        }

        // If both username and password meet the criteria, attempt to add the user to the database
        return database.addUser(new User(userName,0,password,type,address));
    }

    public void logoutUser() {
        AuthenticatedUser.getInstance().setUser(null);
    }


    //don't need this method here
    public boolean setNewPassword(String newPassword) throws UserCreationException {
        User authenticatedUser = AuthenticatedUser.getInstance().getUser();

        if (authenticatedUser == null) {
            throw new UserCreationException("User is not yet authenticated");
        }

        if (newPassword == null || newPassword.length() <= 4) {
            throw new UserCreationException("Password must be longer than 4 characters.");
        }

        authenticatedUser.setPassword(authenticatedUser.getPassword(), newPassword);
        return database.updateUserPassword(authenticatedUser.getUserID(), newPassword);
    }

    public boolean setNewUserName(String newUsername) throws UserCreationException {
        User authenticatedUser = AuthenticatedUser.getInstance().getUser();

        if (authenticatedUser == null) {
            System.out.println("User is not yet authenticated");
            return false;
        }

        // Check if the username length is not greater than 3
        if (newUsername == null || newUsername.length() <= 3) {
            throw new UserCreationException("Username must be longer than 3 characters.");
        }

        authenticatedUser.setUsername(newUsername);
        return database.updateUsername(authenticatedUser.getUserID(), newUsername);
    }

    public boolean setNewUserAddress(String newAddress) {
        User authenticatedUser = AuthenticatedUser.getInstance().getUser();
        authenticatedUser.setAddress(newAddress);
        return database.updateUserAddress(authenticatedUser.getUserID(), newAddress);
    }
}
