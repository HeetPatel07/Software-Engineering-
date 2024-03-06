package com.example.myapplication.persistence.hqsldb;

import android.os.Build;
import android.util.Log;

import com.example.myapplication.Models.Book;
import com.example.myapplication.Models.User;
import com.example.myapplication.business.utlis.RandomGenerator;
import com.example.myapplication.persistence.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookEaseDatabase implements Database {

    private final String dbPath;

    private final List<User> users = new ArrayList<>();
    private final List<Book> books = new ArrayList<>();

    public BookEaseDatabase(String dbPath) {

        this.dbPath = dbPath;
        loadUsers();
        loadBooks();
    }
    public void loadUsers() {
        String usersSql = "SELECT * FROM PUBLIC.USERS";
        try (Connection connection = getConnection(dbPath);
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(usersSql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String address = rs.getString("address");
                String type = rs.getString("type");

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void loadBooks() {
        String booksSql = "SELECT * FROM PUBLIC.BOOKS";
        try (Connection connection = getConnection(dbPath);
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(booksSql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String bookname = rs.getString("bookname");
                String authorName = rs.getString("author_name");
                double price = rs.getBigDecimal("price").doubleValue();
                double edition = rs.getBigDecimal("edition").doubleValue();
                String description = rs.getString("description");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
