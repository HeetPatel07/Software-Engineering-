package com.example.myapplication.Models;

public class Rating {
    private int rating;
    private String comment;
    private  final int  authorID;
    public Rating(int nRating, String nComment){
        rating = nRating;
        comment = nComment;
        authorID = -1;
    }
    public Rating(int nRating, String nComment, int userID){
        rating = nRating;
        comment = nComment;
        authorID = userID;
    }
    public Rating(int nRating, String nComment, User user){
        rating = nRating;
        comment = nComment;
        authorID = user.getUserID();
    }
    public int getRating(){
        return rating;
    }
    public String getComment(){
        return comment;
    }

    public int getAuthorID() { return authorID; }

    public void setRating (int nRating, User currUser){
        //deprecated
        rating = nRating;
    }
    public void setComment (String nComment, User currUser) throws RuntimeException{
        //deprecated
        comment = nComment;
    }
    public void setRating(int newRating){
        rating = newRating;
    }
    public void setRating(String newComment){
        comment = newComment;
    }
    public void setRating(int newRating, String newComment){
        rating = newRating;
        comment = newComment;
    }

}

