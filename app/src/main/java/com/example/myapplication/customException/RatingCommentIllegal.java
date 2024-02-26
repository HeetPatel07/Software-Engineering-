package com.example.myapplication.customException;

public class RatingCommentIllegal extends RatingException{
    static private final String msg = "Comment cannot be empty";
    public RatingCommentIllegal(){
        super(msg);
    }
}
