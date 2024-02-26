package com.example.myapplication.Models;

public class Book {

    private final int id;
    private String bookName;

    private double price;

    private final BookProperties properties;

    private String authorName;


    public Book(int id,String bookName, double price,
                String bookDescription, double edition,
                String authorName) {
        this.id = id;
        this.bookName = bookName;
        this.price = price;
        this.authorName = authorName;
        this.properties = new BookProperties(bookDescription,edition);
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

    public void addRating(int rating, String comment) {
        this.properties.addRating(new Rating(rating, comment));
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


}
