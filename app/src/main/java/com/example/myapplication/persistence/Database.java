package com.example.myapplication.persistence;

import com.example.myapplication.Models.Book;
import com.example.myapplication.Models.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface Database {
    default Connection getConnection(String dbPath) throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true","SA", "");
    }
}
