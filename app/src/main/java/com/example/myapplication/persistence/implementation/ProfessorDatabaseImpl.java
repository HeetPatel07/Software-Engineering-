package com.example.myapplication.persistence.implementation;

import com.example.myapplication.Models.Course;
import com.example.myapplication.persistence.subinterfaces.ProfessorDatabase;

import java.util.List;


/**
 * try {
 *         Connection connection = BookDatabase.super.getConnection();
 *         System.out.println(connection);
 *         }
 *         catch (SQLException e){
 *         }

 * For add, update, delete methods use transactions and write the queries
 */
public class ProfessorDatabaseImpl extends UserDatabaseImpl implements ProfessorDatabase {
    private String dbpath;
    public ProfessorDatabaseImpl(String dbpath){
        super(dbpath);
        this.dbpath = dbpath;
    }
    @Override
    public boolean addTeachingCourses(int userId, int courseId) {
        return false;
    }

    @Override
    public List<Course> getTeachingCourses(int userId) {
        return null;
    }

    @Override
    public boolean addRequiredBookToCourse(int userId, int courseId, int bookId) {
        return false;
    }

    @Override
    public boolean deleteRequiredBookFromCourse(int userId, int courseId, int bookId) {
        return false;
    }
}
