package com.example.myapplication.business.management;

import com.example.myapplication.Models.Book;
import com.example.myapplication.Models.User;
import com.example.myapplication.business.authentication.AuthenticatedUser;
import com.example.myapplication.persistence.subinterfaces.BookDatabase;
import com.example.myapplication.persistence.subinterfaces.SellBooksDatabase;

public class SellBooksManagement {
    BookManagement manager;
    private SellBooksDatabase sellBooksDatabase;
    private User user; //auth user

    public SellBooksManagement(BookDatabase database, SellBooksDatabase sellBooksDatabase, User user) {
        this.manager = new BookManagement(database);
        this.sellBooksDatabase = sellBooksDatabase;
        this.user=user;
    }


    public String bookExists(int id, float price, String condition) {


        Book book = manager.findBookWithID(id);

        if (book != null) {
            addUsedBook(book, price, condition);
            return  book.getBookName();    //returning the book name for the front end to display
        }

        return null;
    }

    private void addUsedBook(Book usedBook, float price, String condition) {
        sellBooksDatabase.addSaleBook(user.getUserID(), usedBook.getId(),condition, price);
        AuthenticatedUser.getInstance().getUser().addForSale(usedBook.addUsedBook(price, condition));
    }
}
