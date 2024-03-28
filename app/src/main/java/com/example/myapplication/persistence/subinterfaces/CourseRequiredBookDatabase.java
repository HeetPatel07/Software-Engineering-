package com.example.myapplication.persistence.subinterfaces;

import com.example.myapplication.Models.Book;
import com.example.myapplication.Models.Course;
import com.example.myapplication.customException.BadCourseException;
import com.example.myapplication.persistence.Database;

import java.util.List;

public interface CourseRequiredBookDatabase extends Database {
    List<Course> getCourseList() throws BadCourseException;

    void addRequiredBookToCourse(String courseName, int bookId) throws BadCourseException;

    void deleteRequiredBookFromCourse(String courseName, int bookId) throws BadCourseException;
}
