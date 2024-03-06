package com.example.myapplication.business.management;

import com.example.myapplication.Models.Course;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CourseManagement {

    // Single instance
    private static CourseManagement instance;

    // Map to store courses
    private Map<String, Course> coursesMap;

    // Private constructor
    private CourseManagement() {
        coursesMap = new HashMap<>();
    }

    // Public method to get the instance
    public static synchronized CourseManagement getInstance() {
        if (instance == null) {
            instance = new CourseManagement();
        }
        return instance;
    }

    public Map<String, Course> getCourses() {
        return coursesMap;
    }

    public Course getCourse(String courseName){
        return coursesMap.get(courseName);
    }

    public Set<Integer> getCourseRequiredBookIDs(String courseName){
        return coursesMap.get(courseName).getRequiredBookSet();
    }

    public Boolean addCourse(String name) {
        if (name != null && name.length() != 0) {
            coursesMap.put(name, new Course(name));
            return true;
        }
        return false;
    }

    public Boolean hasCourse(String name) {
        return coursesMap.containsKey(name);
    }

    public Boolean deleteCourse(String name) {
        if (hasCourse(name)) {
            coursesMap.remove(name);
            return true;
        }
        return false;
    }

    public Boolean addRequiredBookToCourse(String courseName, int bookID) {
        if (!hasCourse(courseName))
            return false;

        Course course = coursesMap.get(courseName);
        Set<Integer> requiredBookSet = course.getRequiredBookSet();
        requiredBookSet.add(bookID);

        return true;
    }

    public Boolean deleteRequiredBookInCourse(String courseName, int bookID) {
        if (!hasCourse(courseName))
            return false;

        Course course = coursesMap.get(courseName);
        Set<Integer> requiredBookSet = course.getRequiredBookSet();
        requiredBookSet.remove(bookID);

        return true;
    }
}
