package com.example.myapplication.business.management;

import com.example.myapplication.Models.Book;
import com.example.myapplication.customException.BookNotFoundException;

import java.util.List;

public interface FindableBook {
    List<Book> viewBooks();
    List<Book> findBooksWithBookName(String bookName) throws BookNotFoundException;
    Book findBookWithID(int id);

    List<Book> findBookWithAuthorName(String authorName);
}
