package com.example.myapplication.business.management;

import com.example.myapplication.Models.Book;

import java.util.List;

public interface FindableBook {
    List<Book> viewBooks();
    List<Book> findBooksWithBookName(String bookName);
    Book findBookWithID(int id);

    List<Book> findBookWithAuthorName(String authorName);
}
