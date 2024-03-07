package com.example.myapplication.business.management;

import com.example.myapplication.Models.Course;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CourseManagement {

    private Map<String, Course> coursesMap;

    public CourseManagement() {
        coursesMap = new HashMap<>();
    }

    public Map<String, Course> getCourses() {
        return new HashMap<>(coursesMap);
    }

    public Course getCourse(String courseName) {
        Course course = coursesMap.get(courseName);
        if (course == null) {
            throw new IllegalArgumentException("Course does not exist: " + courseName);
        }
        return course;
    }

    public Set<Integer> getCourseRequiredBookIDs(String courseName) {
        Course course = getCourse(courseName); // This already throws an exception if the course does not exist
        return course.getRequiredBookSet();
    }

    public boolean addCourse(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Course name cannot be null or empty.");
        }
        if (coursesMap.containsKey(name)) {
            throw new IllegalArgumentException("Course already exists: " + name);
        }
        coursesMap.put(name, new Course(name));
        return true;
    }

    public boolean addRequiredBookToCourse(String courseName, int bookID) {
        Course course = getCourse(courseName);
        return course.getRequiredBookSet().add(bookID);
    }

    public boolean deleteRequiredBookInCourse(String courseName, int bookID) {
        Course course = getCourse(courseName);
        if (!course.getRequiredBookSet().contains(bookID)) {
            throw new IllegalArgumentException("Attempting to delete a non-required book: " + bookID + " from course: " + courseName);
        }
        return course.getRequiredBookSet().remove(bookID);
    }
}
