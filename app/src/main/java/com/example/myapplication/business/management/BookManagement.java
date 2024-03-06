package com.example.myapplication.business.management;

import com.example.myapplication.Models.Book;
import com.example.myapplication.persistence.Database;
import com.example.myapplication.persistence.subinterfaces.BookDatabase;

import java.util.List;
import java.util.Optional;

public class BookManagement {

    protected final BookDatabase database;

    public BookManagement(BookDatabase database) {
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
