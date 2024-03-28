package com.example.myapplication.bussinessITtests;


import static org.junit.Assert.*;


import com.example.myapplication.Models.Book;
import com.example.myapplication.Models.Course;


import com.example.myapplication.application.Services;

import com.example.myapplication.business.management.AuthenticationManager;
import com.example.myapplication.business.management.CourseManagement;
import com.example.myapplication.business.management.AccountManagement;
import com.example.myapplication.customException.BadCourseException;


import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.io.File;
import java.io.IOException;

public class CourseMangementTestIT {
    private File tempDB;

    CourseManagement courseManagement;


    @Before
    public void setup() {

        try {
            this.tempDB = TestUtils.copyDB();
        } catch (IOException e) {
            System.out.println("Error starting the test");
            fail();
        }
        courseManagement = new CourseManagement(Services.getCourseRequiredBookDatabase());
    }

    @Test
    public void getCourseTest() {

        List<Course> result;
        result = courseManagement.getCourse();
        assertTrue(!result.isEmpty());

    }

    private Course getCourse(String courseName) {

        List<Course> courses = courseManagement.getCourse();
        Course course = null;

        // Iterate through the list of courses
        for (Course c : courses) {
            // Check if the current course's name matches the given course name
            if (c.getCourseName().equals(courseName)) {
                // Assign the current course to the course variable
                course = c;
                break; // Exit the loop once the matching course is found
            }
        }

        return course;
    }

    @Test
    public void addRequiredBookToCourseTest() {
        try {
            Course reqCourse = getCourse("Linear Algebra");

            assertNotNull(reqCourse);

            Set<Book> books = reqCourse.getRequiredBookSet();

            courseManagement.addRequiredBookToCourse("Linear Algebra", 4);

            reqCourse = getCourse("Linear Algebra");
            Set<Book> books2 = reqCourse.getRequiredBookSet();
            assertTrue(books.size() < books2.size());
        } catch (BadCourseException e) {
            fail();
        }
    }

    @Test
    public void deleteRequiredBookTest() {

        try {
            courseManagement.addRequiredBookToCourse("Cell Biology", 4);

            Course reqCourse = getCourse("Cell Biology");

            assertNotNull(reqCourse);

            Set<Book> books = reqCourse.getRequiredBookSet();

            courseManagement.deleteRequiredBookInCourse("Cell Biology", 4);

            reqCourse = getCourse("Cell Biology");
            Set<Book> books2 = reqCourse.getRequiredBookSet();
            assertTrue(books.size() > books2.size());
        } catch (BadCourseException e) {
            fail();
        }

    }
}
