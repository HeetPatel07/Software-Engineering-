package com.example.myapplication.Models;

import java.util.ArrayList;

public class BookProperties {
    private final ArrayList<Rating> ratings;

    private String description;

    private String condition;

    private double edition;

    BookProperties(String description, double edition, String condition) {
        ratings = new ArrayList<Rating>();
        this.description = description;
        this.edition = edition;
        this.condition = condition;
    }

    public String getCondition() {
        return condition;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getEdition() {
        return edition;
    }

    public void setEdition(double edition) {
        this.edition = edition;
    }

    public void addRating(Rating rating) {
        ratings.add(rating);
    }

    public int showOverallRating() {
        if (ratings.size() == 0) return 0;
        int total = 0;
        for (Rating rating : ratings) {
            total = total + rating.getRating();
        }
        return total / ratings.size();
    }

    public ArrayList<Rating> getRatings() {
        return ratings;
    }

}