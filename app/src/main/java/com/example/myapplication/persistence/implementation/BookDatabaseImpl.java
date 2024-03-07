package com.example.myapplication.persistence.implementation;

import android.app.admin.SecurityLog;

import com.example.myapplication.Models.Book;

import com.example.myapplication.Models.Rating;
import com.example.myapplication.persistence.subinterfaces.BookDatabase;
import com.example.myapplication.persistence.subinterfaces.RatingDatabase;

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
    private final String dbpath;

//    private final RatingDatabase ratingDatabase;

    public BookDatabaseImpl(String dbpath) {
        this.dbpath = dbpath;


    }

    @Override
    public synchronized List<Book> getBooks() {
        List<Book> bookList = new ArrayList<>();

        String booksSql = "SELECT b.id, b.bookname, b.author_name, BF.price, b.edition ,b.description, BF.book_condition FROM BOOKS b RIGHT JOIN BOOKFORSALE BF on b.id=BF.book_id";

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
    public synchronized List<Book> findBookWithBookName(String bookName) {
        List<Book> bookList = new ArrayList<>();

        String booksSql = "SELECT b.id, b.bookname, b.author_name, b.price, b.edition ,b.description, BF.book_condition FROM BOOKS b INNER JOIN BOOKFORSALE BF on b.id=BF.book_id "
                + "WHERE b.bookname=?";


        try {
            Connection connection = BookDatabase.super.getConnection(dbpath);
            PreparedStatement statement = connection.prepareStatement(booksSql);
            statement.setString(1, bookName);

            ResultSet rs = statement.executeQuery();

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
    public synchronized Optional<Book> findBookWithID(int id) {
        // Correct usage with Statement should carefully handle SQL injection risks.
        // However, this approach is for consistency as per the request.
//        String sql = "SELECT * FROM BOOKS b JOIN BOOKFORSALE FB ON b.id=FB.book_id WHERE id = ?;";

        String sql = "SELECT b.id, b.bookname, b.author_name, b.price, b.edition ,b.description, BF.book_condition AS cond FROM BOOKS b INNER JOIN BOOKFORSALE BF on b.id=BF.book_id WHERE b.id=?";

        try {
            Connection connection = BookDatabase.super.getConnection(dbpath);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                String bookname = rs.getString("bookname");
                String authorName = rs.getString("author_name");
                double price = rs.getBigDecimal("price").doubleValue();
                double edition = rs.getBigDecimal("edition").doubleValue();
                String description = rs.getString("description");
                String condition = rs.getString("cond");
                Book value = new Book(id, bookname, price, description, edition, authorName, condition);

                RatingDatabase ratingDatabase = new RatingDatabaseImpl(dbpath);

                List<Rating> ratingsOfBook = ratingDatabase.getRatingsOfBook(id);

                ratingsOfBook.forEach(value::addRating);

                return Optional.of(value);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }


    @Override
    public synchronized List<Book> findBooksWithAuthorName(String authorName) {
        List<Book> bookList = new ArrayList<>();
        // Directly incorporating variables into the SQL string should be handled with caution.
        String sql = "SELECT * FROM PUBLIC.BOOKS WHERE author_name = ?;";

        try {
            Connection connection = BookDatabase.super.getConnection(dbpath);
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
    public synchronized void addBook(int id, String bookName, double price, String description, double edition, String authorName, String bookCondition) {
        // Building the SQL command by directly including the variables into the command string.
        String sql = "INSERT INTO PUBLIC.BOOKS (bookname, author_name, price, edition, description) VALUES (?, ?, ?, ?, ?)";
        try {
            Connection connection = getConnection(dbpath);

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,bookName);
            statement.setString(2,authorName);
            statement.setDouble(3,price);
            statement.setDouble(4,edition);
            statement.setString(5,description);

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
