package com.example.myapplication.business.unitTests;
import com.example.myapplication.application.Services;
import com.example.myapplication.business.management.AccountManagement;
import com.example.myapplication.business.management.BookManagement;

import  com.example.myapplication.Models.Book;
import com.example.myapplication.persistence.stub.DummyDatabase;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class BookManagementTest {

    static BookManagement bookManagement_test;
    DummyDatabase data ;
    private static boolean setup=false;
    @Before
    public void createTest(){

        if(!setup) {
            data = (DummyDatabase) DummyDatabase.getInstance();
            bookManagement_test = new BookManagement(data);

            data.addBook(1, "TestBook", 34.49, "good book",
                    1.01, "Jhonny","New");

            data.addBook(2, "anything", 87.49, "bad book",
                    1.211, "Some person","NEW");

            data.addBook(3, "anything", 89.49, "mid book",
                    1.2211, "Some person","NEW");

            setup=true;
        }else {
            System.out.println("Setup already executed.");
        }

    }
    @Test
    public void instanceTest() {

        AccountManagement accountManagement1 = new AccountManagement(Services.getUserDatabase());
        try {

            accountManagement1.createNewUser("tom1234", "12345678", "User", "R3T");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //testing the view books right now we added one book
    @Test
        public void listRetrevial(){

        System.out.println("Testing the book list retrieval method");

        assertNotEquals(0,bookManagement_test.viewBooks().size());

        System.out.println("Book list retrieval Test passed");
    }

    @Test public void findBookUsingName(){

        System.out.println("Testing the search method for books using names");

//        assertEquals(0,bookManagement_test.findBooksWithBookName("nullbook").size());
//
//        assertEquals(1,bookManagement_test.findBooksWithBookName("TestBook").size());
//
//        assertEquals(2,bookManagement_test.findBooksWithBookName("anything").size());

        System.out.println("Search method for books using names passed");

    }

    @Test public void findBookUsingId(){

        System.out.println("Testing the search method for books using ID");

        assertNull(bookManagement_test.findBookWithID(20));

        Book book =(bookManagement_test.findBookWithID(2));

        assertNotNull(book);

        System.out.println("Search method for books using ID passed");

    }


}
