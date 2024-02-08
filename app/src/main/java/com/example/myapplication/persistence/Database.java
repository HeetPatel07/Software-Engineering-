package com.example.myapplication.persistence;

import com.example.myapplication.Models.Book;
import com.example.myapplication.Models.User;

import java.util.List;

public interface Database {

    List<User> getUsers();

    List<Book> getBooks();
}
