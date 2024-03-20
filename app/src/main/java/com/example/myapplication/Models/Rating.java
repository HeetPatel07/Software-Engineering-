package com.example.myapplication.Models;

public class Rating {
    private int rating;
    private String comment;
    private String name;
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
    public Rating(int nRating, String nComment, int userID,String name){
        if(nRating < 0 || nRating > 5) throw new RuntimeException("Rating must between 0 and 5");
        rating = nRating;
        comment = nComment;
        authorID=userID;
        this.name =name;
    }

    public int getRating(){
        return rating;
    }
    public String getComment(){
        return comment;
    }

    public String getAuthorName(){return name;}

    public void setRating(int newRating){
        rating = newRating;
    }
    public void setRating(String newComment){
        comment = newComment;
    }

}