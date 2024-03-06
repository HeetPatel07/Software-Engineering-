package com.example.myapplication.persistence.implementation;

import com.example.myapplication.Models.Book;
import com.example.myapplication.persistence.subinterfaces.SellBooksDatabase;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * try {
 *         Connection connection = BookDatabase.super.getConnection();
 *         System.out.println(connection);
 *         }
 *         catch (SQLException e){
 *         }

 * For add, update, delete methods use transactions and write the queries
 */
public class SellBooksDatabaseImpl implements SellBooksDatabase {
    private final String dbpath;
    public SellBooksDatabaseImpl(String dbpath){
        this.dbpath = dbpath;
    }
    @Override
    public List<Book> getBooksForSale(int userId) {
        return null;
    }

    @Override
    public boolean deleteSaleBook(int userId, int bookId) {
        // Constructing SQL query directly with variables (be cautious of SQL injection)
        String sql = "DELETE FROM PUBLIC.BOOKFORSALE WHERE user_id = " + userId + " AND book_id = " + bookId;

        try (Connection connection = getConnection(dbpath);
             Statement statement = connection.createStatement()) {

            connection.setAutoCommit(false); // Start transaction
            try {
                int rowsAffected = statement.executeUpdate(sql); // Execute the update
                connection.commit(); // Commit transaction

                return rowsAffected > 0; // Return true if rows were deleted

            } catch (SQLException e) {
                connection.rollback(); // Rollback transaction in case of error
                e.printStackTrace();
                return false;
            } finally {
                connection.setAutoCommit(true); // Restore auto-commit mode
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public boolean addSaleBook(int userId, int bookId, String bookCondition, double price) {
        String sql = "INSERT INTO PUBLIC.BOOKFORSALE (book_id, user_id, book_condition, price) VALUES ("
                + bookId + ", " + userId + ", '" + bookCondition + "', " + price + ")";
        try (Connection connection = getConnection(dbpath);
             Statement statement = connection.createStatement()) {

            connection.setAutoCommit(false); // Start transaction
            try {
                int rowsAffected = statement.executeUpdate(sql); // Execute the insertion
                connection.commit(); // Commit transaction

                return rowsAffected > 0; // Return true if rows were inserted

            } catch (SQLException e) {
                connection.rollback(); // Rollback transaction in case of error
                e.printStackTrace();
                return false;
            } finally {
                connection.setAutoCommit(true); // Restore auto-commit mode
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}


