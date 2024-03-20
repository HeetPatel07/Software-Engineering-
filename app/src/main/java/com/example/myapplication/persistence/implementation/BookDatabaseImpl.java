package com.example.myapplication.persistence.implementation;

import com.example.myapplication.Models.Book;

import com.example.myapplication.Models.Rating;
import com.example.myapplication.customException.BookCreationException;
import com.example.myapplication.customException.BookNotFoundException;
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

    public BookDatabaseImpl(String dbpath) {
        this.dbpath = dbpath;
    }

    @Override
    public synchronized List<Book> getBooks() {
        List<Book> bookList = new ArrayList<>();

        String booksSql = "SELECT b.id, b.bookname, b.author_name, BF.price, b.edition ,b.description, BF.book_condition FROM BOOKS b RIGHT JOIN BOOKFORSALE BF on b.id=BF.book_id";

        try (Connection connection = BookDatabase.super.getConnection(dbpath); Statement statement = connection.createStatement(); ResultSet rs = statement.executeQuery(booksSql)) {
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


    public synchronized List<Book> findBooksWithBookName(String bookName) throws BookNotFoundException {
        List<Book> bookList = new ArrayList<>();

        String booksSql = "SELECT b.id, b.bookname, b.author_name, BF.price, b.edition, b.description, BF.book_condition FROM BOOKS b JOIN BOOKFORSALE BF ON b.id=BF.book_id WHERE LOWER(b.bookname) LIKE LOWER(?)";

        try (Connection connection = BookDatabase.super.getConnection(dbpath);
             PreparedStatement statement = connection.prepareStatement(booksSql)) {

            // Concatenate '%' to bookName to search for any occurrence
            String likeParam = "%" + bookName + "%";
            statement.setString(1, likeParam);

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

        if (bookList.isEmpty()) {
            throw new BookNotFoundException("Couldn't find book with name: " + bookName);
        }

        return bookList;
    }

    @Override
    public synchronized Optional<Book> findBookWithID(int id) throws BookNotFoundException {

        String sql = "SELECT b.id, b.bookname, b.author_name, BF.price, b.edition ,b.description, BF.book_condition AS cond FROM BOOKS b INNER JOIN BOOKFORSALE BF on b.id=BF.book_id WHERE b.id=?";

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
        throw new BookNotFoundException("Book with ID : " + id + " was not Found");
    }


    @Override
    public void addBook(Book addBook) throws BookCreationException {
        String sql = "INSERT INTO PUBLIC.BOOKS (bookname, author_name, price, edition, description) VALUES (?, ?, ?, ?, ?)";
        try {
            Connection connection = getConnection(dbpath);

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, addBook.getBookName());
            statement.setString(2, addBook.getAuthorName());
            statement.setDouble(3, addBook.getPrice());
            statement.setDouble(4, addBook.getBookEdition());
            statement.setString(5, addBook.getDescription());

            connection.setAutoCommit(false); // Start transaction
            try {
                statement.executeUpdate(sql);
                connection.commit(); // Commit transaction
            } catch (SQLException e) {
                connection.rollback(); // Rollback transaction in case of error
                throw e;
            } finally {
                connection.setAutoCommit(true); // Restore auto-commit mode
                connection.close();
            }
        } catch (SQLException e) {
            throw new BookCreationException("Unable to add book into the table " + addBook.getBookName());
        }
    }
}

