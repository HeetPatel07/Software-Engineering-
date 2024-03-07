package com.example.myapplication.Models;

import java.util.HashSet;
import java.util.Set;

public class Course {
    private int courseID;
    private String courseName;

    private final Set<Integer> requiredBookSet; //stored as book ids

    public Course(String courseName){
        this.courseName = courseName;
        requiredBookSet = new HashSet<Integer>();
    }

    public Course(int courseID, String courseName){
        this.courseID = courseID;
        this.courseName = courseName;
        requiredBookSet= new HashSet<Integer>();
    }
    public int getCourseID(){
        return courseID;
    }

    public String getCourseName(){
        return courseName;
    }

    public Set<Integer> getRequiredBookSet(){
        return requiredBookSet;
    }

    public void setCourseID(int id){
        courseID= id;
    }

    public void setCourseName(String courseName){
        this.courseName = courseName;
    }

}
