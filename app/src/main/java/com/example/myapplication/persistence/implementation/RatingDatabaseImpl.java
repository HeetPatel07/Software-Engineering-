package com.example.myapplication.persistence.implementation;

import com.example.myapplication.Models.Rating;
import com.example.myapplication.persistence.subinterfaces.RatingDatabase;

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
    private String dbpath;
    public RatingDatabaseImpl(String dbpath){
        this.dbpath = dbpath;
    }
    @Override
    public void addRating(int userId, int bookId, int rating, String comment) {

    }
    @Override
    public List<Rating> getRatingsOfBook(int bookId) {
        return null;
    }

//    @Override
//    public void deleteRating(int userId, int ratingId) {
//
//    }
}
