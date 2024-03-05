package com.example.myapplication.application;


import com.example.myapplication.persistence.hqsldb.BookEaseDatabase;
import com.example.myapplication.persistence.Database;

public class Services {
    private static Database bookEaseDatabase;
    public static synchronized Database getBookEaseDatabase() {
        if(bookEaseDatabase == null) {
            bookEaseDatabase = new BookEaseDatabase(Main.getDBPathName());
        }
        return bookEaseDatabase;
    }
}

