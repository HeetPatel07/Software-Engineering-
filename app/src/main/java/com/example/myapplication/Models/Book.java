package com.example.myapplication.Models;

public class Book {

    private final int id;
    private String bookName;

    private double price;

    private final BookProperties properties;

    private String authorName;


    public Book(int id,String bookName, double price,
                String bookDescription, double edition,
                String authorName,String condition) {
        this.id = id;
        this.bookName = bookName;
        this.price = price;
        this.authorName = authorName;
        this.properties = new BookProperties(bookDescription,edition,condition);
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

    public void addRating(int rating, String comment) {
        this.properties.addRating(new Rating(rating, comment,1));
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

//point for making sure right branch is rebased