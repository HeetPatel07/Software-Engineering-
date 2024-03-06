package com.example.myapplication.business;

import static org.junit.Assert.*;

import com.example.myapplication.business.management.CourseManagement;

import org.junit.Before;
import org.junit.Test;


public class CourseManagementTest {

    private CourseManagement cm;

    @Before
    public void setUp() {
        cm = CourseManagement.getInstance();
        cm.getCourses().clear();
    }

    @Test
    public void addCourseTest() {
        assertTrue(cm.addCourse("Math"));
        assertTrue(cm.hasCourse("Math"));
        assertFalse(cm.addCourse(null));
        assertFalse(cm.addCourse(""));
    }

    @Test
    public void getCourseTest() {
        cm.addCourse("Physics");
        assertNotNull(cm.getCourse("Physics"));
        assertNull(cm.getCourse("Chemistry"));
    }

    @Test
    public void getCourseRequiredBookIDsTest() {
        cm.addCourse("Literature");
        cm.addRequiredBookToCourse("Literature", 101);
        assertTrue(cm.getCourseRequiredBookIDs("Literature").contains(101));
    }

    @Test
    public void deleteCourseTest() {
        cm.addCourse("Math");
        assertFalse(cm.deleteCourse(null));
        assertFalse(cm.deleteCourse(""));
        assertFalse(cm.deleteCourse("English"));
        assertTrue(cm.deleteCourse("Math"));
    }

    @Test
    public void addRequiredBookToCourseTest() {
        cm.addCourse("Science");
        assertTrue(cm.addRequiredBookToCourse("Science", 202));
        assertTrue(cm.getCourse("Science").getRequiredBookSet().contains(202));
    }

    @Test
    public void deleteRequiredBookInCourseTest() {
        cm.addCourse("History");
        cm.addRequiredBookToCourse("History", 303);
        assertTrue(cm.deleteRequiredBookInCourse("History", 303));
        assertFalse(cm.getCourse("History").getRequiredBookSet().contains(303));
    }

    @Test
    public void hasCourseTest() {
        cm.addCourse("Geography");
        assertTrue(cm.hasCourse("Geography"));
        assertFalse(cm.hasCourse("Biology"));
    }
}
