package com.example.myapplication.customException;

public class RatingNotAuthor extends RatingException{
    private static final String msg = "Current user is not the author of this rating";
    public RatingNotAuthor(){
        super(msg);
    }
}
