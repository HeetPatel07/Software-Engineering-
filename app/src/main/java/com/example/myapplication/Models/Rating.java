package com.example.myapplication.Models;

import com.example.myapplication.customException.BadRatingException;

public class Rating {
    private int rating;
    private String comment;
    private String name;
    private final int userID;


    public Rating(int nRating, String nComment, int userID) {
        rating = nRating;
        comment = nComment;
        this.userID = userID;
    }


    public Rating(int nRating, String nComment, int userID, String name) throws BadRatingException {
        if (nRating < 0 || nRating > 5)
            throw new BadRatingException("Rating must between 0 and 5");
        rating = nRating;
        comment = nComment;
        this.userID = userID;
        this.name = name;
    }

    public int getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    public String getAuthorName() {
        return name;
    }

    public void setRating(int newRating) {
        rating = newRating;
    }

    public void setRating(String newComment) {
        comment = newComment;
    }

}