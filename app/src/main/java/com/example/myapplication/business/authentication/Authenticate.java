package com.example.myapplication.business.authentication;

import com.example.myapplication.persistence.exceptions.UserNotFoundException;

public interface Authenticate {

    boolean authenticateUser(String username, String password) throws UserNotFoundException;

}
