package com.example.myapplication.persistence.implementation;

import com.example.myapplication.Models.Book;
import com.example.myapplication.persistence.subinterfaces.FavoriteBooksDatabase;
import com.example.myapplication.persistence.subinterfaces.SellBooksDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
public class SellBooksDatabaseImpl implements SellBooksDatabase {
    private final String dbpath;

    public SellBooksDatabaseImpl(String dbpath) {
        this.dbpath = dbpath;
    }

    @Override
    public List<Book> getBooksForSale(int userId) {
        List<Book> bookList = new ArrayList<>();

        String sql = "SELECT b.id, b.bookname, b.author_name, BF.price, b.edition, b.description, BF.book_condition FROM BOOKS b JOIN BOOKFORSALE BF ON BF.book_id = b.id WHERE BF.user_id = ?";

        try (Connection connection = SellBooksDatabase.super.getConnection(dbpath);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, userId);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String bookName = rs.getString("bookname");
                String authorName = rs.getString("author_name");
                double price = rs.getBigDecimal("price").doubleValue();
                double edition = rs.getBigDecimal("edition").doubleValue();
                String description = rs.getString("description");
                String bookCondition = rs.getString("book_condition");
                bookList.add(new Book(id, bookName, price, description, edition, authorName, bookCondition));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookList;
    }

    @Override
    public boolean deleteSaleBook(int userId, int bookId) {
        String sql = "DELETE FROM PUBLIC.BOOKFORSALE WHERE user_id = ? AND book_id = ?";

        try (Connection connection = getConnection(dbpath);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, userId);
            statement.setInt(2, bookId);
            int rowsAffected = statement.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean addSaleBook(int userId, int bookId, String bookCondition, double price) {
        String sql = "INSERT INTO PUBLIC.BOOKFORSALE (book_id, user_id, book_condition, price) VALUES (?, ?, ?, ?)";

        try (Connection connection = getConnection(dbpath);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, bookId);
            statement.setInt(2, userId);
            statement.setString(3, bookCondition);
            statement.setDouble(4, price);
            int rowsAffected = statement.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}


