package com.example.myapplication.business;

import com.example.myapplication.Models.User;

public class AuthenticatedUser {

    private static final AuthenticatedUser authenticatedUser = new AuthenticatedUser();

    private User user ;

    private AuthenticatedUser (){

    }

    public static AuthenticatedUser getInstance(){
       return authenticatedUser;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
