package com.example.myapplication.business;

import com.example.myapplication.Models.User;
import com.example.myapplication.persistence.DummyDatabase;

import java.util.Optional;

public class AuthenticationManager implements Authenticate{

    private final DummyDatabase database;

    public AuthenticationManager(DummyDatabase database) {
        this.database = database;
    }

    @Override
    public boolean authenticateUser(String username, String password) {
        Optional<User> userOptional = this.database.findUserWithUsername(username);
        if(userOptional.isPresent()){
            User user = userOptional.get();
            if(user.checkPassword(password)){
                AuthenticatedUser authenticatedUser = AuthenticatedUser.getInstance();
                authenticatedUser.setUser(user);
                return true;
            }
        }
        return false;
    }
}
