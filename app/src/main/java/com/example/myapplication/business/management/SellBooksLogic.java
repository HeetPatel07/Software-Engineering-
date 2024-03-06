package com.example.myapplication.business.management;

import com.example.myapplication.Models.Book;
import com.example.myapplication.business.authentication.AuthenticatedUser;
import com.example.myapplication.persistence.subinterfaces.BookDatabase;

public class SellBooksLogic extends BookManagement {
    private final BookManagement manager;

    public SellBooksLogic(BookDatabase database) {
        super(database);
        this.manager = new BookManagement(database);
    }


    public String bookExists(int id, float price, String condition) {

        Book object = manager.findBookWithID(id);

        if (object != null) {
            addUsedBook(object, price, condition);
            return  object.getBookName();    //returning the book name for the front end to display
        }

        return null;
    }

    private void addUsedBook(Book usedBook, float price, String condition) {
        AuthenticatedUser.getInstance().getUser().addForSale(usedBook.addUsedBook(price, condition));
    }
}
