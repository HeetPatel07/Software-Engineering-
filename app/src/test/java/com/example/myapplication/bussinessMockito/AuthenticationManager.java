package com.example.myapplication.bussinessMockito;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import com.example.myapplication.Models.User;
import com.example.myapplication.business.authentication.AuthenticatedUser;
import com.example.myapplication.customException.UserNotFoundException;
import com.example.myapplication.persistence.implementation.UserDatabaseImpl;
import com.example.myapplication.persistence.subinterfaces.UserDatabase;

public class AuthenticationManager {

    private com.example.myapplication.business.management.AuthenticationManager authenticationManager;
    private UserDatabase userDatabase;

    @Before
    public void setUp() {
        userDatabase = mock(UserDatabaseImpl.class);
        authenticationManager = new com.example.myapplication.business.management.AuthenticationManager(userDatabase);
    }

    @Test
    public void testAuthenticateUser() throws UserNotFoundException {
        // Mock user data
        String username = "testuser";
        String password = "testpassword";
        User user = new User(username, 1, password, "type", "address");

        // Mock database behavior to return the user when queried with the username
        when(userDatabase.findUserWithUsername(username)).thenReturn(Optional.of(user));

        // Authenticate the user
        boolean isAuthenticated = authenticationManager.authenticateUser(username, password);

        // Assert that authentication was successful
        assertTrue(isAuthenticated);

        // Assert that the authenticated user is set in the AuthenticatedUser singleton
        assertTrue(AuthenticatedUser.getInstance().getUser().equals(user));
    }

    @Test
    public void testAuthenticateUser_UserNotFound() throws UserNotFoundException {
        // Mock user data
        String username = "nonexistentuser";
        String password = "testpassword";

        when(userDatabase.findUserWithUsername(username)).thenReturn(Optional.empty());

        authenticationManager.authenticateUser(username, password);
    }
}
