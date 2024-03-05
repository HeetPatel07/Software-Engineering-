package com.example.myapplication.persistence.subinterfaces;

import com.example.myapplication.Models.Book;
import com.example.myapplication.persistence.Database;

import java.util.List;
import java.util.Optional;

public interface BookDatabase extends Database {
    List<Book> getBooks();
    List<Book> findBookWithBookName(String bookName);
    Optional<Book> findBookWithID(int id);
    List<Book> findBooksWithAuthorName(String authorName);
    void addBook(int id, String bookName, double price, String description, double edition, String authorName, String bookCondition);

}
