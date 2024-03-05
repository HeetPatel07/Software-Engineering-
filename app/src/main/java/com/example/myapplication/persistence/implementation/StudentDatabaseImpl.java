package com.example.myapplication.persistence.implementation;

import com.example.myapplication.Models.Book;
import com.example.myapplication.Models.User;
import com.example.myapplication.persistence.subinterfaces.StudentDatabase;

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
public class StudentDatabaseImpl extends UserDatabaseImpl implements StudentDatabase {
    private String dbpath;
    public StudentDatabaseImpl(String dbpath){
        super(dbpath);
        this.dbpath = dbpath;
    }
    @Override
    public List<Book> viewRequiredBooks(int userId, int courseId) {
        return null;
    }

    @Override
    public boolean addCourse(int userId, int courseId) {
        return false;
    }
}
