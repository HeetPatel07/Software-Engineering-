package com.example.myapplication.customException;

public class RatingRatingIllegal extends RatingException {
    private static final String msg = "Rating must between 0 and 5";
    public RatingRatingIllegal(){
        super(msg);
    }
}
