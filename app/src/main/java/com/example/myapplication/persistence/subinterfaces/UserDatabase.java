package com.example.myapplication.persistence.subinterfaces;

import com.example.myapplication.Models.User;
import com.example.myapplication.persistence.Database;

import java.util.Optional;

public interface UserDatabase extends Database {

    Optional<User> findUserWithUsername(String username);

    boolean updateUserPassword(User user, String newPassword);

    boolean addUser(String userName, String nPassword, String nType, String nAddress);


}
