package com.example.myapplication.persistence.implementation;

import com.example.myapplication.Models.Book;
import com.example.myapplication.customException.BookCreationException;
import com.example.myapplication.customException.BookNotFoundException;
import com.example.myapplication.persistence.subinterfaces.FavoriteBooksDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class FavoriteBooksDatabaseImpl implements FavoriteBooksDatabase {
    private String dbpath;

    public FavoriteBooksDatabaseImpl(String dbpath) {
        this.dbpath = dbpath;
    }

    @Override
    public synchronized List<Book> getFavoriteBooks(int userId) throws BookNotFoundException {
        List<Book> bookList = new ArrayList<>();

        String sql;

        sql = "SELECT b.id, b.bookname AS book_name, b.author_name, BS.price, b.edition, b.description, BS.book_condition FROM BOOKS b INNER JOIN FAVOURITEBOOK BF on b.id = BF.book_id INNER JOIN BOOKFORSALE BS ON b.id = BS.book_id WHERE BF.user_id = ?;";

        try {
            Connection connection = FavoriteBooksDatabase.super.getConnection(dbpath);

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String bookName = rs.getString("book_name");
                String authorName = rs.getString("author_name");
                double price = rs.getBigDecimal("price").doubleValue();
                double edition = rs.getBigDecimal("edition").doubleValue();
                String description = rs.getString("description");
                String bookCondition = rs.getString("book_condition");
                bookList.add(new Book(id, bookName, price, description, edition, authorName, bookCondition));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BookNotFoundException("Error in loading you favourite books");
        }
        return bookList;
    }

    @Override
    public synchronized boolean addFavoriteBook(int userId, int bookId) throws BookCreationException {

        if (checkIfFavoriteBookExists(userId, bookId)) {
            throw new BookCreationException("Favourite book already in the table");
        }
        String sql = "INSERT INTO PUBLIC.FAVOURITEBOOK (book_id, user_id) VALUES (?, ?);";

        try {
            Connection connection = FavoriteBooksDatabase.super.getConnection(dbpath);
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, bookId);
            statement.setInt(2, userId);

            connection.setAutoCommit(false); // Start transaction
            try {
                statement.executeUpdate();
                connection.commit(); // Commit transaction
                return true;
            } catch (SQLException e) {
                connection.rollback(); // Rollback transaction in case of error
                throw e;
            } finally {
                connection.setAutoCommit(true); // Restore auto-commit mode
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public synchronized boolean deleteFavoriteBook(int userId, int bookId) {
        if (!checkIfFavoriteBookExists(userId, bookId)) {
            throw new IllegalArgumentException("Favourite not in the table");
        }
        String sql = "DELETE FROM FAVOURITEBOOK WHERE book_id = ? AND user_id = ?;";

        try {
            Connection connection = FavoriteBooksDatabase.super.getConnection(dbpath);
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, bookId);
            statement.setInt(2, userId);

            connection.setAutoCommit(false); // Start transaction
            try {
                statement.executeUpdate();
                connection.commit(); // Commit transaction
                return true;
            } catch (SQLException e) {
                connection.rollback(); // Rollback transaction in case of error
                throw e;
            } finally {
                connection.setAutoCommit(true); // Restore auto-commit mode
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private synchronized boolean checkIfFavoriteBookExists(int userId, int bookId) {
        String sql = "SELECT book_id , user_id FROM FAVOURITEBOOK FB WHERE FB.book_id = ? AND FB.user_id = ?";

        try {
            Connection connection = FavoriteBooksDatabase.super.getConnection(dbpath);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, bookId);
            statement.setInt(2, userId);

            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
