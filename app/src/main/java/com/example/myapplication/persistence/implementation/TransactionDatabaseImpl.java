package com.example.myapplication.persistence.implementation;

import com.example.myapplication.Models.Book;
import com.example.myapplication.Models.Transaction;
import com.example.myapplication.Models.User;
import com.example.myapplication.business.authentication.AuthenticatedUser;
import com.example.myapplication.customException.CheckoutException;
import com.example.myapplication.persistence.subinterfaces.TransactionDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransactionDatabaseImpl implements TransactionDatabase {

    private final String dbpath;

    public TransactionDatabaseImpl(String dbpath) {
        this.dbpath = dbpath;
    }

    @Override
    public synchronized List<Transaction> getPurchaseHistory(User user) throws CheckoutException {

        List<Transaction> purchaseHistory = new ArrayList<>();

        String sql;
        Book bookSold;


        sql = "SELECT t.book_id, t.amount, t.address,b.bookname AS book_name, b.author_name, b.edition FROM TRANSACTIONS t JOIN BOOKS b ON b.id = t.book_id WHERE t.user_id = ?";

            try {
                Connection connection = TransactionDatabase.super.getConnection(dbpath);

                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setInt(1, user.getUserID());

                ResultSet rs = statement.executeQuery();
                while (rs.next()) {

                    int id = rs.getInt("book_id");
                    String deliveredTo = rs.getString("address");
                    String bookName = rs.getString("book_name");
                    String authorName = rs.getString("author_name");
                    double price = rs.getBigDecimal("amount").doubleValue();
                    double edition = rs.getBigDecimal("edition").doubleValue();

                    bookSold = new Book(id, bookName, price, null, edition, authorName, null);
                    purchaseHistory.add(new Transaction(deliveredTo, price, bookSold));
                }
            } catch (SQLException e) {
                System.out.println("Error in reading the past transactions");
                throw new CheckoutException("Error in loading the past transactions");
            }

        return purchaseHistory;
    }

    @Override
    public synchronized boolean deleteBookForSale(User user, Book book) throws CheckoutException {
        String deleteBookSql = "DELETE FROM BOOKFORSALE WHERE book_id = ? ";
        String insertTransactionSql = "INSERT INTO TRANSACTIONS (user_id, book_id, amount, address) VALUES (?, ?, ?, ?)";

        Connection connection = null;
        PreparedStatement deleteStatement = null;
        PreparedStatement insertStatement = null;

        try {
            connection = TransactionDatabase.super.getConnection(dbpath);
            connection.setAutoCommit(false); // Start transaction

            // Delete book from the books for sale
            deleteStatement = connection.prepareStatement(deleteBookSql);
            deleteStatement.setInt(1, book.getId());
            int rowsDeleted = deleteStatement.executeUpdate();

            if (rowsDeleted == 0) {
                throw new CheckoutException("Failed to delete the book from the user's sale list.");
            }

            //  INSERT INTO TRANSACTIONS VALUES (9,3, 10, 84.75,'990 Pembina Road')

            // Insert transaction data into the TRANSACTIONS table
            insertStatement = connection.prepareStatement(insertTransactionSql);
            insertStatement.setInt(1, user.getUserID());
            insertStatement.setInt(2, book.getId());
            insertStatement.setDouble(3, book.getPrice());
            insertStatement.setString(4, user.getAddress());
            insertStatement.executeUpdate();

            connection.commit(); // Commit transaction
            return true;
        } catch (SQLException e) {
            try {
                if (connection != null) {
                    connection.rollback(); // Rollback if any exception occurs
                }
            } catch (SQLException rollbackEx) {
                System.err.println("Error rolling back transaction: " + rollbackEx.getMessage());
            }
            throw new CheckoutException("Error deleting book for sale and adding transaction: " + e.getMessage());
        } finally {
            // Close resources
            try {
                if (deleteStatement != null) {
                    deleteStatement.close();
                }
                if (insertStatement != null) {
                    insertStatement.close();
                }
                if (connection != null) {
                    connection.setAutoCommit(true); // Reset auto-commit mode
                    connection.close();
                }
            } catch (SQLException closeEx) {
                System.err.println("Error closing resources: " + closeEx.getMessage());
            }
        }
    }


}

