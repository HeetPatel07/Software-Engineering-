package com.example.myapplication.business;

import com.example.myapplication.Models.Book;
import com.example.myapplication.persistence.subinterfaces.BookDatabase;

import java.util.List;
import java.util.Optional;

public class BookManagement {

    private final BookDatabase dummyDatabase;

    public BookManagement(BookDatabase dummyDatabase) {
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

    public boolean buyBook(int bookID){
        return buyBook(findBookWithID(bookID));
    }
    public boolean buyBook(Book book){
        if(book != null && book.stockpile >0) {
            book.stockpile --;
            return true;
        } else {
            return false;
        }
    }
    public boolean sellBook(int bookID){
        return sellBook(findBookWithID(bookID));
    }
    public boolean sellBook(Book book){
        if(book != null) {
            book.stockpile++;
            return true;
        } else {
            return false;
        }
    }

}
