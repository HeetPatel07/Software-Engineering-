package com.example.myapplication.persistence.implementation;

import com.example.myapplication.Models.User;
import com.example.myapplication.persistence.subinterfaces.UserDatabase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

/**
 * try {
 *         Connection connection = BookDatabase.super.getConnection();
 *         System.out.println(connection);
 *         }
 *         catch (SQLException e){
 *         }

 * For add, update, delete methods use transactions and write the queries
 */
public class UserDatabaseImpl implements UserDatabase {

    protected String dbpath;

    public UserDatabaseImpl(String dbpath){
        this.dbpath = dbpath;
    }
    public Optional<User> findUserWithUsername(String username) {
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
        return Optional.empty();
    }

    @Override
    public boolean updateUserPassword(int userID, String newPassword) {
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
    public boolean updateUsername(int userID, String newUsername) {
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
    public boolean updateUserAddress(int userID, String newAddress) {
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
    public boolean addUser(String userName, String nPassword, String nType, String nAddress) {
        String sql = String.format("INSERT INTO PUBLIC.USERS (username, password, address, type) VALUES ('%s', '%s', '%s', '%s')",
                userName.replace("'", "''"), nPassword.replace("'", "''"), nAddress.replace("'", "''"), nType.replace("'", "''"));
        try (Connection connection = getConnection(dbpath);
             Statement statement = connection.createStatement()) {

            int rowsAffected = statement.executeUpdate(sql);
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}