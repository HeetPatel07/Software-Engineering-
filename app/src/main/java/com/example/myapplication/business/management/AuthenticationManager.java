package com.example.myapplication.business.management;

import com.example.myapplication.Models.User;
import com.example.myapplication.business.authentication.Authenticate;
import com.example.myapplication.business.authentication.AuthenticatedUser;
import com.example.myapplication.customException.UserNotFoundException;
import com.example.myapplication.persistence.subinterfaces.UserDatabase;

import java.util.Optional;

public class AuthenticationManager implements Authenticate {

    private final UserDatabase database;

    public AuthenticationManager(UserDatabase database) {
        this.database = database;
    }

    @Override
    public boolean authenticateUser(String username, String password) {
        Optional<User> userOptional = null;
        try {
            userOptional = this.database.findUserWithUsername(username);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                if (user.checkPassword(password)) {
                    AuthenticatedUser authenticatedUser = AuthenticatedUser.getInstance();
                    authenticatedUser.setUser(user);
                    return true;
                }
            }
        } catch (UserNotFoundException e) {
            System.out.println("The user is not authenticated");
        }

        return false;
    }
}
