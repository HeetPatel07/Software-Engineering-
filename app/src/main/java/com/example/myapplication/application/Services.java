package com.example.myapplication.application;


import com.example.myapplication.persistence.implementation.BookDatabaseImpl;
import com.example.myapplication.persistence.implementation.CourseRequiredBookDatabaseImpl;
import com.example.myapplication.persistence.implementation.FavoriteBooksDatabaseImpl;
import com.example.myapplication.persistence.implementation.SellBooksDatabaseImpl;
import com.example.myapplication.persistence.implementation.UserDatabaseImpl;
import com.example.myapplication.persistence.subinterfaces.BookDatabase;
import com.example.myapplication.persistence.subinterfaces.CourseRequiredBookDatabase;
import com.example.myapplication.persistence.subinterfaces.FavoriteBooksDatabase;
import com.example.myapplication.persistence.subinterfaces.SellBooksDatabase;
import com.example.myapplication.persistence.subinterfaces.UserDatabase;

public class Services {
    private static BookDatabase bookDatabase;
    private static UserDatabase userDatabase;
    private static SellBooksDatabase sellBooksDatabase;

    private static FavoriteBooksDatabaseImpl favBookDatabase;

    private static CourseRequiredBookDatabaseImpl courseRequiredBoookDatabase;

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

    public static synchronized FavoriteBooksDatabase getFavBooksDatabase() {
        if(favBookDatabase == null) {
            favBookDatabase = new FavoriteBooksDatabaseImpl(Main.getDBPathName());
        }
        return favBookDatabase;
    }

    public static synchronized CourseRequiredBookDatabase getCourseRequiredBookDatabase(){
        if(courseRequiredBoookDatabase == null){
            courseRequiredBoookDatabase = new CourseRequiredBookDatabaseImpl(Main.getDBPathName());
        }
        return courseRequiredBoookDatabase;
    }

}

