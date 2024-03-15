package com.example.myapplication.business.management;

import com.example.myapplication.Models.Book;
import com.example.myapplication.persistence.exceptions.BookNotFoundException;
import com.example.myapplication.persistence.subinterfaces.BookDatabase;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class BookManagement implements FindableBook,Sortable {

    protected final BookDatabase database;

    public BookManagement(BookDatabase database) {
        this.database = database;
    }

    public List<Book> viewBooks(){
        return database.getBooks();
    }

    public List<Book> findBooksWithBookName(String bookName) throws BookNotFoundException {
        List<Book>result=null;

        try {
        database.findBooksWithBookName(bookName);
        }catch(BookNotFoundException e){
            throw new BookNotFoundException("No books with name found");
        }
        return result;
    }

    public Book findBookWithID(int id){

        Optional<Book> bookWithID =null;
        try {
            bookWithID = database.findBookWithID(id);
        }catch(BookNotFoundException e){
            System.out.println("No books with ");
        }
        return bookWithID.orElse(null);
    }

    @Override
    public List<Book> findBookWithAuthorName(String authorName) {
        List<Book> result=null;
        try {
            return database.findBooksWithAuthorName(authorName);
        }catch(BookNotFoundException e){
            System.out.println("Error in finding the books by"+authorName);
        }
        return result;
    }
    @Override
    public List<Book> sortByPrice(List<Book> originalist) {
        return originalist.stream().sorted(Comparator.comparingDouble(Book::getPrice)).toList();
    }
    @Override
    public List<Book> sortByBookName(List<Book> originalist) {
        return originalist.stream().sorted(Comparator.comparing(Book::getBookName)).toList();
    }
    @Override
    public List<Book> sortByRating(List<Book> originalist) {
        return originalist.stream().sorted(Comparator.comparingDouble(Book::getOverallBookRating)).toList();
    }
}
