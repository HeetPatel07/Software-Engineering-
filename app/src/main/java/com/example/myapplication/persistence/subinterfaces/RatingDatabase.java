package com.example.myapplication.persistence.subinterfaces;

import com.example.myapplication.Models.Rating;
import com.example.myapplication.customException.BadRatingException;
import com.example.myapplication.persistence.Database;

import java.util.List;

public interface RatingDatabase extends Database {

    List<Rating> getRatingsOfBook(int bookId) throws BadRatingException;

}
