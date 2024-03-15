package com.example.myapplication.Models;

import java.util.HashSet;
import java.util.Set;

public class Course {
    private int courseID;
    private String courseName;

    private final Set<Book> requiredBookSet; //stored as book ids

    public Course(String courseName){
        this.courseName = courseName;
        requiredBookSet = new HashSet<Book>();
    }

    public void addRequiredBook(Book book){
        requiredBookSet.add(book);
    }

    public int getCourseID(){
        return courseID;
    }

    public String getCourseName(){
        return courseName;
    }

    public Set<Book> getRequiredBookSet(){
        return requiredBookSet;
    }

    public void setCourseID(int id){
        courseID= id;
    }

    public void setCourseName(String courseName){
        this.courseName = courseName;
    }

}
