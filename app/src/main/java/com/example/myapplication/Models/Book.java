package com.example.myapplication.Models;

import java.util.ArrayList;
import java.util.List;

public class Book {

    private final int id;
    private String bookName;

    private double price;

    private final BookProperties properties;

    private String authorName;

    public int stockpile;

    public Book(int id,String bookName, double price,
                String bookDescription, double edition,
                String authorName,String condition) {
        this.id = id;
        this.bookName = bookName;
        this.price = price;
        this.authorName = authorName;
        this.properties = new BookProperties(bookDescription,edition,condition);
        stockpile = 0;
    }
    public Book addUsedBook(double price,String condition){

        Book cpy= new Book(this.getId(),this.bookName,price,this.getDescription(),this.properties.getEdition(),this.getAuthorName(),condition);
        return cpy;
    }

    public int getId() {
        return id;
    }

    public String getBookName() {
        return bookName;
    }

    public int getOverallBookRating(){
        return properties.showOverallRating();
    }

    public String getDescription(){
        return properties.getDescription();
    }

    public String getAuthorName() {
        return authorName;
    }

    public void addRating(int rating, String comment,int userID) {
        this.properties.addRating(new Rating(rating, comment,userID));
    }

    public void addRating(Rating rating){
        this.properties.addRating(rating);
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }




    public double getBookEdition() {
        return this.properties.getEdition();
    }

    public ArrayList<Rating> getRatings(){
        return properties.getRatings();
    }
}
