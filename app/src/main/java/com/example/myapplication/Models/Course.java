package com.example.myapplication.Models;

import java.util.HashSet;
import java.util.Set;

public class Course {
    private String courseName;

    private final Set<Book> requiredBookSet; //stored as book ids

    public Course(String courseName) {
        this.courseName = courseName;
        requiredBookSet = new HashSet<Book>();
    }

    public void addRequiredBook(Book book) {
        requiredBookSet.add(book);
    }

    public String getCourseName() {
        return courseName;
    }

    public Set<Book> getRequiredBookSet() {
        return requiredBookSet;
    }
}
