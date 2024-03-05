package com.example.myapplication.persistence.subinterfaces;

import com.example.myapplication.Models.User;
import com.example.myapplication.persistence.Database;

import java.util.Optional;

public interface UserDatabase extends Database {

    Optional<User> findUserWithUsername(String username);

    boolean updateUserPassword(int userID, String newPassword);
    boolean updateUsername(int userID, String newUsername);
    boolean updateUserAddress(int userID, String newAddress);

    boolean addUser(String userName, String nPassword, String nType, String nAddress);


}
