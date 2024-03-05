package com.example.myapplication.business;

import com.example.myapplication.Models.Book;
import com.example.myapplication.persistence.Database;

import java.util.List;
import java.util.Optional;

public class BookManagement {

    private final Database database;

    public BookManagement(Database database) {
        this.database = database;
    }

    public List<Book> viewBooks(){
        return database.getBooks();
    }

    public List<Book> findBooksWithBookName(String bookName){
        return database.findBookWithBookName(bookName);
    }

    public Book findBookWithID(int id){
        Optional<Book> bookWithID = database.findBookWithID(id);
        return bookWithID.orElse(null);
    }

}
