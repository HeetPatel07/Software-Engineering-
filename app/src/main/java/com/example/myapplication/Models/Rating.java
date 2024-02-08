package com.example.myapplication.Models;

public class Rating {
    private int rating;
    private String comment;

    private  final int  authorID;

    public Rating(int nRating, String nComment){
        if(nRating < 0 || nRating > 5) throw new RuntimeException("Rating must between 0 and 5");
        rating = nRating;
        comment = nComment;
        authorID = -1;
    }
    public Rating(int nRating, String nComment, int userID){
        if(nRating < 0 || nRating > 5) throw new RuntimeException("Rating must between 0 and 5");
        rating = nRating;
        comment = nComment;
        authorID = userID;
    }
    public Rating(int nRating, String nComment, User user){
        if(user == null) throw new RuntimeException("Rating must contain a User");
        if(nRating < 0 || nRating > 5) throw new RuntimeException("Rating must between 0 and 5");
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

    public void setRating (int nRating, User currUser) throws RuntimeException{
        if(currUser != null && authorID == currUser.getUserID()){
            if(nRating < 0 || nRating > 5) throw new RuntimeException("Rating must between 0 and 5");
            rating = nRating;
        } else {
            throw new RuntimeException("Current user is not the author of this comment");
        }
    }
    public void setComment (String nComment, User currUser) throws RuntimeException{
        if(currUser != null && authorID == currUser.getUserID()){
            comment = nComment;
        } else {
            throw new RuntimeException("Current user is not the author of this comment");
        }
    }
}

