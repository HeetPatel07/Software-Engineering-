package com.example.myapplication.persistence.implementation;

import com.example.myapplication.Models.Rating;
import com.example.myapplication.persistence.subinterfaces.RatingDatabase;

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
 * }

 * For add, delete methods use transactions and write the queries
 */
public class RatingDatabaseImpl implements RatingDatabase {
    private final String dbpath;
    public RatingDatabaseImpl(String dbpath){
        this.dbpath = dbpath;
    }
    @Override

    //comment
    public synchronized boolean addRating(int userId, int bookId, int rating, String comment) {

        String sql = "INSERT INTO PUBLIC.COMMENTS(book_id,user_id,rating,comment_text) VALUES (?,?,?,?);";

        try {
            Connection connection = RatingDatabase.super.getConnection(dbpath);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,bookId);
            statement.setInt(2,userId);
            statement.setDouble(3,rating);
            statement.setString(4,comment);

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
        }
        return false;

    }
    @Override
    public synchronized List<Rating> getRatingsOfBook(int bookId) {

        String sql = "SELECT R.rating , R.comment_text , R.user_id FROM COMMENTS R where R.book_id = ?;";
        List<Rating> ratings = new ArrayList<>();

        try{
            Connection connection = RatingDatabase.super.getConnection(dbpath);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,bookId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                double rating = resultSet.getDouble("rating");
                String commentText = resultSet.getString("comment_text");
                int user_id = resultSet.getInt("user_id");

                ratings.add(new Rating((int)rating,commentText,user_id));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return ratings;
    }

//    @Override
//    public void deleteRating(int userId, int ratingId) {
//
//    }
}
