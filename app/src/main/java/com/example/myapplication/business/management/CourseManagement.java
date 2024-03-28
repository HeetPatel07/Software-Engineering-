package com.example.myapplication.business.management;

import com.example.myapplication.Models.Course;
import com.example.myapplication.customException.BadCourseException;
import com.example.myapplication.persistence.subinterfaces.CourseRequiredBookDatabase;

import java.util.List;

public class CourseManagement {

    private CourseRequiredBookDatabase courseRequiredBookDatabase;

    public CourseManagement(CourseRequiredBookDatabase database) {
        this.courseRequiredBookDatabase = database;
    }

    public List<Course> getCourse() {
        List<Course> result = null;

        try {
            result = courseRequiredBookDatabase.getCourseList();
        } catch (BadCourseException e) {
            System.out.println("Error of get course list from database.");
        }
        return result;
    }

    public void addRequiredBookToCourse(String courseName, int bookID)  throws BadCourseException {
        try {
            courseRequiredBookDatabase.addRequiredBookToCourse(courseName, bookID);
        } catch (BadCourseException e) {
            System.out.print("Error in adding required book to course: " + courseName);
        }
    }

    public void deleteRequiredBookInCourse(String courseName, int bookID) throws BadCourseException  {
        try {
            courseRequiredBookDatabase.deleteRequiredBookFromCourse(courseName, bookID);
        } catch (BadCourseException e) {
            System.out.print("Error of delete required book in Course: " + courseName);
        }
    }
}
