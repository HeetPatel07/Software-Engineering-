package com.example.myapplication.persistence.subinterfaces;

import com.example.myapplication.Models.User;
import com.example.myapplication.persistence.Database;
import com.example.myapplication.customException.UserCreationException;
import com.example.myapplication.customException.UserNotFoundException;

import java.util.Optional;

public interface UserDatabase extends Database {

    Optional<User> findUserWithUsername(String username) throws UserNotFoundException;

    boolean updateUserPassword(int userID, String newPassword);

    boolean updateUsername(int userID, String newUsername);

    boolean updateUserAddress(int userID, String newAddress);

    boolean addUser(String userName, String nPassword, String nType, String nAddress);

    void addUser(User user) throws UserCreationException;

}
