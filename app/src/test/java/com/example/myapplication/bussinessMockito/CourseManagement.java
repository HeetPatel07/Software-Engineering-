package com.example.myapplication.bussinessMockito;

import com.example.myapplication.Models.Course;
import com.example.myapplication.customException.BadCourseException;
import com.example.myapplication.persistence.subinterfaces.CourseRequiredBookDatabase;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class CourseManagement {

    private CourseRequiredBookDatabase courseRequiredBookDatabase;
    private com.example.myapplication.business.management.CourseManagement courseManagement;

    @Before
    public void setUp() {
        courseRequiredBookDatabase = mock(CourseRequiredBookDatabase.class);
        courseManagement = new com.example.myapplication.business.management.CourseManagement(courseRequiredBookDatabase);
    }

    @Test
    public void testGetCourse() {
        // Mock CourseRequiredBookDatabase to return a list of courses
        List<Course> expectedCourses = new ArrayList<>();
        Course course1 = new Course("Course 1");
        Course course2 = new Course("Course 2");
        expectedCourses.add(course1);
        expectedCourses.add(course2);
        try {
            when(courseRequiredBookDatabase.getCourseList()).thenReturn(expectedCourses);
        }catch (BadCourseException e ){
            fail();
        }

        // Call the method under test
        List<Course> result = courseManagement.getCourse();

        // Verify that the result matches the expected courses
        assertEquals(expectedCourses, result);
    }

    @Test
    public void testAddRequiredBookToCourse() {
        // Call the method under test
        try {
            courseManagement.addRequiredBookToCourse("Course 1", 1);

            // Verify that addRequiredBookToCourse method was called with correct parameters
            verify(courseRequiredBookDatabase, times(1)).addRequiredBookToCourse("Course 1", 1);
        }catch (BadCourseException e ){
            fail();
        }
    }

    @Test
    public void testDeleteRequiredBookInCourse() {
        try {
            // Call the method under test
            courseManagement.deleteRequiredBookInCourse("Course 1", 1);

            // Verify that deleteRequiredBookInCourse method was called with correct parameters
            verify(courseRequiredBookDatabase, times(1)).deleteRequiredBookFromCourse("Course 1", 1);
        }catch (BadCourseException e ){
                fail();
            }
    }

    @Test
    public void testGetCourse_EmptyList() {
        try {
            // Mock CourseRequiredBookDatabase to return an empty list of courses
            List<Course> expectedCourses = new ArrayList<>();
            when(courseRequiredBookDatabase.getCourseList()).thenReturn(expectedCourses);

            // Call the method under test
            List<Course> result = courseManagement.getCourse();

            // Verify that the result is an empty list
            assertNotNull(result);
            assertTrue(result.isEmpty());
        }catch (BadCourseException e ){
            fail();
        }
    }
    @Test
    public void testAddRequiredBookToCourse_NullCourseName() {
        try {
            // Call the method under test with a null course name
            courseManagement.addRequiredBookToCourse(null, 1);

            // Verify that no interaction with the database occurred
            verify(courseRequiredBookDatabase, never()).addRequiredBookToCourse(anyString(), anyInt());
        }catch (BadCourseException e ){
            fail();
        }
    }

    @Test
    public void testDeleteRequiredBookInCourse_NullCourseName() {
        try {
            // Call the method under test with a null course name
            courseManagement.deleteRequiredBookInCourse(null, 1);

            // Verify that no interaction with the database occurred
            verify(courseRequiredBookDatabase, never()).deleteRequiredBookFromCourse(anyString(), anyInt());
        }catch (BadCourseException e ){
            fail();
        }
    }

}
