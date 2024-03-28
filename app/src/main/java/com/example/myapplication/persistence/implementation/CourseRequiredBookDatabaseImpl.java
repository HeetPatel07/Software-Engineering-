package com.example.myapplication.persistence.implementation;

import com.example.myapplication.Models.Book;
import com.example.myapplication.Models.Course;
import com.example.myapplication.customException.BadCourseException;
import com.example.myapplication.persistence.subinterfaces.CourseRequiredBookDatabase;
import com.example.myapplication.persistence.subinterfaces.FavoriteBooksDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseRequiredBookDatabaseImpl implements CourseRequiredBookDatabase {
    private String dbpath;

    public CourseRequiredBookDatabaseImpl(String dbpath) {
        this.dbpath = dbpath;
    }

    @Override
    public List<Course> getCourseList() {
        List<Course> courseList = new ArrayList<>();
        String sql;
        sql = "SELECT c.course_id, c.courseName, b.id AS book_id, b.bookname AS book_name, b.author_name, b.price, b.edition, b.description FROM PUBLIC.COURSES c JOIN PUBLIC.BOOKS b ON c.book_id = b.id;";
        Map<String, Course> courseMap = new HashMap<>();
        try {
            Connection connection = CourseRequiredBookDatabase.super.getConnection(dbpath);
            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("course_id");
                String courseName = rs.getString("courseName");

                int bookId = rs.getInt("book_id");
                String bookname = rs.getString("book_name");
                String authorName = rs.getString("author_name");
                double price = rs.getBigDecimal("price").doubleValue();
                double edition = rs.getBigDecimal("edition").doubleValue();
                String description = rs.getString("description");
                Course course = new Course(courseName);
                Book book = new Book(bookId, bookname, price, description, edition, authorName, null);
                if (courseMap.containsKey(courseName)) {
                    courseMap.get(courseName).addRequiredBook(book);
                } else {
                    course.addRequiredBook(book);
                    courseMap.put(courseName, course);
                }
            }

            courseMap.forEach((courseName, course) -> {
                courseList.add(course);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return courseList;
    }


    public void addRequiredBookToCourse(String courseName, int bookId) throws BadCourseException {
        String sql = "INSERT INTO PUBLIC.COURSES (courseName, book_id) VALUES (?, ?);";
        try (Connection connection = CourseRequiredBookDatabase.super.getConnection(dbpath);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, courseName); // Assuming Course has an getId() method.
            statement.setInt(2, bookId);
            statement.executeUpdate();
        } catch (Exception e) {
            throw new BadCourseException("Invalid course Name : " + courseName + "Or book id :" + bookId);
        }
    }

    public void deleteRequiredBookFromCourse(String courseName, int bookId) throws BadCourseException {
        String sql = "DELETE FROM PUBLIC.COURSES WHERE courseName = ? AND book_id = ?;";
        try (Connection connection = CourseRequiredBookDatabase.super.getConnection(dbpath);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, courseName); // Assuming Course has an getId() method.
            statement.setInt(2, bookId);
            statement.executeUpdate();
        } catch (Exception e) {
            throw new BadCourseException("Invalid course Name : " + courseName + "Or book id :" + bookId);
        }
    }

}
