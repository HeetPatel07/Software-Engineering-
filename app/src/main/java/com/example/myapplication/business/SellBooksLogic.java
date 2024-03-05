package com.example.myapplication.business;

import com.example.myapplication.Models.Book;
import com.example.myapplication.persistence.Database;

public class SellBooksLogic extends BookManagement {
    BookManagement manager;

    public SellBooksLogic(Database dummyDatabase) {
        super(dummyDatabase);
        this.manager = new BookManagement(dummyDatabase);
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
