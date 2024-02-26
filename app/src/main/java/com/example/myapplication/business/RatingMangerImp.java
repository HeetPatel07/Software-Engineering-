package com.example.myapplication.business;

import com.example.myapplication.Models.Rating;
import com.example.myapplication.Models.User;
import com.example.myapplication.customException.RatingCommentIllegal;
import com.example.myapplication.customException.RatingException;
import com.example.myapplication.customException.RatingNotAuthor;
import com.example.myapplication.customException.RatingRatingIllegal;

public class RatingMangerImp implements RatingManger {
    private static final int ratingMax = 5;
    private static final int ratingMin = 0;
    @Override
    public Rating newrating(int rating, String comment) throws RatingException {
        inputCheck(rating, comment);
        return new Rating(rating, comment);
    }
    @Override
    public Rating newrating(int rating, String comment, int userID) throws RatingException{
        inputCheck(rating, comment);
        return new Rating(rating, comment, userID);
    }
    @Override
    public Rating newrating(int rating, String comment, User user) throws RatingException {
        inputCheck(rating, comment);
        if(user == null) return new Rating(rating, comment);
        return new Rating(rating, comment, user);
    }
    @Override
    public void setRating(Rating rating, int newRating, String newComment, int userID) throws RatingException{
        inputCheck(newRating, newComment);
        authorCheck(rating, userID);
        rating.setRating(newRating, newComment);
    }
    @Override
    public void setRating(Rating rating, int newRating,int userID) throws RatingException{
        inputCheck(newRating);
        authorCheck(rating, userID);
        rating.setRating(newRating);
    }
    @Override
    public void setRating(Rating rating, String comment, int userID) throws RatingException{
        inputCheck(comment);
        authorCheck(rating, userID);
        rating.setRating(comment);
    }

    private void inputCheck(int rating, String comment) throws RatingException{
        inputCheck(rating);
        inputCheck(comment);
    }
    private void inputCheck(int rating) throws RatingRatingIllegal{
        if( rating < ratingMin || rating > ratingMax) throw new RatingRatingIllegal();
    }
    private void inputCheck(String comment) throws RatingCommentIllegal {
        if( comment == null || comment.length() == 0) throw new RatingCommentIllegal();
    }
    private void authorCheck(Rating rating, int userID) throws RatingNotAuthor{
        if(rating.getAuthorID() != userID) throw new RatingNotAuthor();
    }
}