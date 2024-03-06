package com.example.myapplication.persistence.implementation;

import android.app.admin.SecurityLog;

import com.example.myapplication.Models.Book;
import com.example.myapplication.persistence.subinterfaces.BookDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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
public class BookDatabaseImpl implements BookDatabase {
    private String dbpath;

    public BookDatabaseImpl(String dbpath) {
        this.dbpath = dbpath;
        loadBooksFromDB();
    }

    private void loadBooksFromDB() {

    }

    @Override
    public List<Book> getBooks() {
        List<Book> bookList = new ArrayList<>();

        String booksSql = "SELECT b.id, b.bookname, b.author_name, b.price, b.edition ,b.description, BF.book_condition FROM BOOKS b RIGHT JOIN BOOKFORSALE BF on b.id=BF.book_id";

        try (Connection connection = BookDatabase.super.getConnection(dbpath);
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(booksSql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String bookname = rs.getString("bookname");
                String authorName = rs.getString("author_name");
                double price = rs.getBigDecimal("price").doubleValue();
                double edition = rs.getBigDecimal("edition").doubleValue();
                String description = rs.getString("description");
                String bookCondition = rs.getString("book_condition");
                bookList.add(new Book(id, bookname, price, description, edition, authorName, bookCondition));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookList;
    }


    @Override
    public List<Book> findBookWithBookName(String bookName) {
        List<Book> bookList = new ArrayList<>();

        String booksSql = "SELECT b.id, b.bookname, b.author_name, b.price, b.edition ,b.description, BF.book_condition FROM BOOKS b RIGHT JOIN BOOKFORSALE BF on b.id=BF.book_id"
                + "WHERE b.bookname='" + bookName + "'";


        try (Connection connection = getConnection(dbpath);
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(booksSql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String bookname = rs.getString("bookname");
                String authorName = rs.getString("author_name");
                double price = rs.getBigDecimal("price").doubleValue();
                double edition = rs.getBigDecimal("edition").doubleValue();
                String description = rs.getString("description");
                String bookCondition = rs.getString("book_condition");
                bookList.add(new Book(id, bookname, price, description, edition, authorName, bookCondition));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookList;
    }

    @Override
    public Optional<Book> findBookWithID(int id) {
        // Correct usage with Statement should carefully handle SQL injection risks.
        // However, this approach is for consistency as per the request.
        String sql = "SELECT * FROM PUBLIC.BOOKS WHERE id = " + id;
        try (Connection connection = getConnection(dbpath);
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {

            if (rs.next()) {
                String bookname = rs.getString("bookname");
                String authorName = rs.getString("author_name");
                double price = rs.getBigDecimal("price").doubleValue();
                double edition = rs.getBigDecimal("edition").doubleValue();
                String description = rs.getString("description");
                return Optional.of(new Book(id, bookname, price, description, edition, authorName, null));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }


    @Override
    public List<Book> findBooksWithAuthorName(String authorName) {
        List<Book> bookList = new ArrayList<>();
        // Directly incorporating variables into the SQL string should be handled with caution.
        String sql = "SELECT * FROM PUBLIC.BOOKS WHERE author_name = ?;";

        try {
            Connection connection= BookDatabase.super.getConnection(dbpath);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, authorName);
            ResultSet rs = statement.executeQuery(sql);
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String bookname = rs.getString("bookname");
                    double price = rs.getBigDecimal("price").doubleValue();
                    double edition = rs.getBigDecimal("edition").doubleValue();
                    String description = rs.getString("description");
                    bookList.add(new Book(id, bookname, price, description, edition, authorName, null));
                }



        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookList;
    }


    @Override
    public void addBook(int id, String bookName, double price, String description, double edition, String authorName, String bookCondition) {
        // Building the SQL command by directly including the variables into the command string.
        String sql = String.format("INSERT INTO PUBLIC.BOOKS (id, bookname, author_name, price, edition, description) VALUES (%d, '%s', '%s', %f, %f, '%s')",
                id, bookName.replace("'", "''"), authorName.replace("'", "''"), price, edition, description.replace("'", "''"));

        try (Connection connection = getConnection(dbpath);
             Statement statement = connection.createStatement()) {

            connection.setAutoCommit(false); // Start transaction
            try {
                statement.executeUpdate(sql);
                connection.commit(); // Commit transaction
            } catch (SQLException e) {
                connection.rollback(); // Rollback transaction in case of error
                throw e;
            } finally {
                connection.setAutoCommit(true); // Restore auto-commit mode
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
