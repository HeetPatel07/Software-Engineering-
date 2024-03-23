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
 * Connection connection = BookDatabase.super.getConnection();
 * System.out.println(connection);
 * }
 * catch (SQLException e){
 * }
 * ;
 * For add, delete methods use transactions and write the queries
 */
public class RatingDatabaseImpl implements RatingDatabase {
    private final String dbpath;

    public RatingDatabaseImpl(String dbpath) {
        this.dbpath = dbpath;
    }

    @Override
    public synchronized List<Rating> getRatingsOfBook(int bookId) {

        String sql = "SELECT R.rating , R.comment_text , R.user_id ,usr.username FROM COMMENTS R JOIN USERS usr on usr.id = R.user_id where R.book_id = ?;";
        List<Rating> ratings = new ArrayList<>();

        try {
            Connection connection = RatingDatabase.super.getConnection(dbpath);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, bookId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String userName = resultSet.getString("username");
                double rating = resultSet.getDouble("rating");
                String commentText = resultSet.getString("comment_text");
                int user_id = resultSet.getInt("user_id");

                ratings.add(new Rating((int) rating, commentText, user_id, userName));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ratings;
    }

}
