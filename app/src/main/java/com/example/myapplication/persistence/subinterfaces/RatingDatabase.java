package com.example.myapplication.persistence.subinterfaces;

import com.example.myapplication.Models.Rating;
import com.example.myapplication.persistence.Database;

import java.util.List;

public interface RatingDatabase extends Database {

    boolean addRating(int userId,int bookId, int rating,String comment);

//    void deleteRating(int userId, int ratingId); // Only Do this if rating id field is present

    List<Rating> getRatingsOfBook(int bookId);

}
