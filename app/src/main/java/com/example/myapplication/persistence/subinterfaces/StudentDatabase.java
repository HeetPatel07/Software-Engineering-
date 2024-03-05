package com.example.myapplication.persistence.subinterfaces;

import com.example.myapplication.Models.Book;

import java.util.List;

public interface StudentDatabase extends UserDatabase {

    List<Book> viewRequiredBooks(int userId, int courseId);

    boolean addCourse(int userId,int courseId); // check if the course already exists then add the
                                                // course in the middle table.


}
