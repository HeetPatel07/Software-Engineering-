package com.example.myapplication.persistence.subinterfaces;

import com.example.myapplication.Models.Book;
import com.example.myapplication.persistence.Database;
import com.example.myapplication.customException.BookCreationException;
import com.example.myapplication.customException.BookNotFoundException;

import java.util.List;
import java.util.Optional;

public interface BookDatabase extends Database {
    List<Book> getBooks();
    List<Book> findBooksWithBookName(String bookName) throws BookNotFoundException;
    Optional<Book> findBookWithID(int id) throws BookNotFoundException;
    List<Book> findBooksWithAuthorName(String authorName) throws BookNotFoundException;
    void addBook(int id, String bookName, double price, String description, double edition, String authorName, String bookCondition);
    void addBook(Book addBook) throws BookCreationException;

}
