package com.example.myapplication.persistence.subinterfaces;

import com.example.myapplication.Models.Course;

import java.util.List;

public interface ProfessorDatabase extends UserDatabase {

    boolean addTeachingCourses(int userId, int courseId);

    List<Course> getTeachingCourses(int userId);

    boolean addRequiredBookToCourse(int userId, int courseId, int bookId);

    boolean deleteRequiredBookFromCourse(int userId, int courseId, int bookId);


}
