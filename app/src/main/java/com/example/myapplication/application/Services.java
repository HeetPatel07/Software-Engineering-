package com.example.myapplication.application;


import com.example.myapplication.persistence.hqsldb.BookEaseDatabase;
import com.example.myapplication.persistence.Database;
import com.example.myapplication.persistence.implementation.BookDatabaseImpl;
import com.example.myapplication.persistence.implementation.SellBooksDatabaseImpl;
import com.example.myapplication.persistence.implementation.UserDatabaseImpl;
import com.example.myapplication.persistence.subinterfaces.BookDatabase;
import com.example.myapplication.persistence.subinterfaces.BuyBookDatabase;
import com.example.myapplication.persistence.subinterfaces.SellBooksDatabase;
import com.example.myapplication.persistence.subinterfaces.UserDatabase;

public class Services {
    private static Database bookEaseDatabase;
    private static BookDatabase bookDatabase;
    private static UserDatabase userDatabase;
    private static SellBooksDatabase sellBooksDatabase;

    public static synchronized BookDatabase getBookDatabase(){
        if(bookDatabase == null){
            bookDatabase = new BookDatabaseImpl(Main.getDBPathName());
        }
        return bookDatabase;
    }

    public static synchronized UserDatabase getUserDatabase() {
        if(userDatabase == null) {
            userDatabase = new UserDatabaseImpl(Main.getDBPathName());
        }
        return userDatabase;
    }

    public static synchronized SellBooksDatabase getSellBooksDatabase() {
        if(sellBooksDatabase == null) {
            sellBooksDatabase = new SellBooksDatabaseImpl(Main.getDBPathName());
        }
        return sellBooksDatabase;
    }

}

