package com.example.myapplication.persistence.implementation;

import com.example.myapplication.Models.Book;
import com.example.myapplication.persistence.subinterfaces.FavoriteBooksDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
public class FavoriteBooksDatabaseImpl implements FavoriteBooksDatabase {
    private String dbpath;
    public FavoriteBooksDatabaseImpl(String dbpath){
        this.dbpath = dbpath;
    }
    @Override
    public synchronized List<Book> getFavoriteBooks(int userId) {
        List<Book> bookList = new ArrayList<>();

        String sql="SELECT b.id, b.bookname, b.author_name, b.price, b.edition ,b.description, BS.book_condition FROM BOOKS b INNER JOIN  FAVOURITEBOOK BF on b.id=BF.book_id INNER JOIN BOOKFORSALE BS  WHERE BF.user_id = ?;";

        try{
            Connection connection = FavoriteBooksDatabase.super.getConnection(dbpath);

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,userId);

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
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return bookList;
    }

    @Override
    public synchronized boolean addFavoriteBook(int userId, int bookId) {

        if(checkIfFavoriteBookExists(userId, bookId)){
            return false;
        }
        String sql = "INSERT INTO PUBLIC.FAVOURITEBOOK (book_id, user_id) VALUES (?, ?);";

        try {
            Connection connection = FavoriteBooksDatabase.super.getConnection(dbpath);
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1,bookId);
            statement.setInt(2,userId);

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
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public synchronized boolean deleteFavoriteBook(int userId, int bookId) {
        if(checkIfFavoriteBookExists(userId, bookId)){
            return false;
        }
        String sql = "DELETE FROM PUBLIC.FAVOURITEBOOK (book_id, user_id) VALUES (?, ?);";

        try {
            Connection connection = FavoriteBooksDatabase.super.getConnection(dbpath);
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1,bookId);
            statement.setInt(2,userId);

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
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    private synchronized boolean checkIfFavoriteBookExists(int userId,int bookId){
        String sql = "SELECT book_id , user_id FROM FAVOURITEBOOK FB WHERE FB.book_id = ? AND FB.user_id = ?";

        try {
            Connection connection = FavoriteBooksDatabase.super.getConnection(dbpath);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,bookId);
            statement.setInt(2, userId);

            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return true;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
