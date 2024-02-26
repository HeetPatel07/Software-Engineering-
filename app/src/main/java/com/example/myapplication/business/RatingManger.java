package com.example.myapplication.business;

import com.example.myapplication.Models.Rating;
import com.example.myapplication.Models.User;
import com.example.myapplication.customException.RatingException;

public interface RatingManger {
    //Rating must between 0 and 5, comment must longer than 0 char.
    //And when setting the Rating, the userID must be the same as the rating's author's ID
    //Else this will throw some exception
    //rating Exception contain
    /*
    RatingCommentIllegal
    RatingRatingIllegal
    RatingNotAuthor
     */
    Rating newrating(int rating, String comment) throws RatingException;
    Rating newrating(int rating, String comment, int userID) throws RatingException;
    Rating newrating(int rating, String comment, User user) throws RatingException;
    void setRating(Rating rating, int newRating, String newComment, int userID) throws RatingException;
    void setRating(Rating rating, int newRating,int userID) throws RatingException;
    void setRating(Rating rating, String comment, int userID) throws RatingException;
}
