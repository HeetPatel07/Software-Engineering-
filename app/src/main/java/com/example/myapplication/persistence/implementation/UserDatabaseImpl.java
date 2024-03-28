package com.example.myapplication.persistence.implementation;

import com.example.myapplication.Models.User;
import com.example.myapplication.customException.UserCreationException;
import com.example.myapplication.customException.UserNotFoundException;
import com.example.myapplication.persistence.subinterfaces.UserDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

/**
 * try {
 * Connection connection = BookDatabase.super.getConnection();
 * System.out.println(connection);
 * }
 * catch (SQLException e){
 * }
 * <p>
 * For add, update, delete methods use transactions and write the queries
 */
public class UserDatabaseImpl implements UserDatabase {

    protected String dbpath;

    public UserDatabaseImpl(String dbpath) {
        this.dbpath = dbpath;
    }

    public synchronized Optional<User> findUserWithUsername(String username) throws UserNotFoundException {
        String sql = "SELECT * FROM PUBLIC.USERS WHERE username = '" + username.replace("'", "''") + "'";
        try (Connection connection = getConnection(dbpath);
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {

            if (rs.next()) {
                int id = rs.getInt("id");
                String userName = rs.getString("username");
                String password = rs.getString("password");
                String address = rs.getString("address");
                String type = rs.getString("type");
                User user = new User(userName, id, password, type, address);
                return Optional.of(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new UserNotFoundException("User with username " + username + " was not found");
    }

    @Override
    public synchronized boolean updateUserPassword(int userID, String newPassword) {
        String sql = "UPDATE PUBLIC.USERS SET password = '" + newPassword.replace("'", "''") + "' WHERE id = " + userID;
        try (Connection connection = getConnection(dbpath);
             Statement statement = connection.createStatement()) {

            int rowsAffected = statement.executeUpdate(sql);
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public synchronized boolean updateUsername(int userID, String newUsername) {
        String sql = "UPDATE PUBLIC.USERS SET username = '" + newUsername.replace("'", "''") + "' WHERE id = " + userID;
        try (Connection connection = getConnection(dbpath);
             Statement statement = connection.createStatement()) {
            //check how many rows in database is affected
            int rowsAffected = statement.executeUpdate(sql);
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public synchronized boolean updateUserAddress(int userID, String newAddress) {
        String sql = "UPDATE PUBLIC.USERS SET address = '" + newAddress.replace("'", "''") + "' WHERE id = " + userID;
        try (Connection connection = getConnection(dbpath);
             Statement statement = connection.createStatement()) {

            //check how many rows in database is affected
            int rowsAffected = statement.executeUpdate(sql);
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public synchronized boolean addUser(User user) throws UserCreationException {

        String sql = "INSERT INTO PUBLIC.USERS (username,password,address,type) VALUES (?,?,?,?);";
        Connection connection = null;
        try {
            connection = getConnection(dbpath);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getAddress());
            statement.setString(4, user.getType());

            connection.setAutoCommit(false); // Start transaction
            try {
                int rowsAffected = statement.executeUpdate();
                connection.commit(); // Commit transaction
                return rowsAffected > 0;
            } catch (SQLException e) {
                connection.rollback(); // Rollback transaction in case of error
                throw e;
            } finally {
                connection.setAutoCommit(true); // Restore auto-commit mode
                connection.close();
            }

        } catch (SQLException e) {
            throw new UserCreationException("Cannot insert user with username " + user.getUsername() + "into the table");
        }
    }
}
