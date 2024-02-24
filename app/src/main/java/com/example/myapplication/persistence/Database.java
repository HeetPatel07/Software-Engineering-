package com.example.myapplication.persistence;

import com.example.myapplication.Models.Book;
import com.example.myapplication.Models.User;

import java.util.List;
import java.util.Optional;

public interface Database {

    List<User> getUsers();

    List<Book> getBooks();
    Optional<User> findUserWithUsername(String username);

    List<Book> findBookWithBookName(String bookName);
    Optional<Book> findBookWithID(int id);

    boolean addUser(String userName, String nPassword, String nType, String nAddress);

    void addBook(int id, String bookName, double price, String description, double edition, String authorName);
}
