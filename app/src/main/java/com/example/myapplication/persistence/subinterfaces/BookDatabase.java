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

    Book findBookWithID(int id) throws BookNotFoundException;
}
