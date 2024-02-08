package com.example.myapplication.business;

import com.example.myapplication.Models.Book;
import com.example.myapplication.persistence.DummyDatabase;

import java.util.List;
import java.util.Optional;

public class BookManagement {

    private final DummyDatabase dummyDatabase;

    public BookManagement(DummyDatabase dummyDatabase) {
        this.dummyDatabase = dummyDatabase;
    }

    public List<Book> viewBooks(){
        return dummyDatabase.getBooks();
    }

    public List<Book> findBooksWithBookName(String bookName){
        return dummyDatabase.findBookWithBookName(bookName);
    }

    public Book findBookWithID(int id){
        Optional<Book> bookWithID = dummyDatabase.findBookWithID(id);
        return bookWithID.orElse(null);
    }

}