package com.example.myapplication.bussinessITtests;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import com.example.myapplication.Models.Book;
import com.example.myapplication.Models.Transaction;
import com.example.myapplication.Models.User;
import com.example.myapplication.application.Services;
import com.example.myapplication.business.authentication.AuthenticatedUser;
import com.example.myapplication.business.management.CheckoutManagement;
import com.example.myapplication.customException.CheckoutException;
import com.example.myapplication.persistence.subinterfaces.SellBooksDatabase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.io.File;
import java.io.IOException;


public class CheckoutManagenemtTestIT {


    private File tempDB;

    @Before
    public void setup() throws IOException {
        System.out.println("Starting integration test for AccessRecipes");
        this.tempDB = TestUtils.copyDB();

        cm = new CheckoutManagement(Services.getTransactionDatabase());
        book1 = new Book(1, "book1", 0.1d, "description1", 1.0d, "book1Author", "New");
        book2 = new Book(2, "book2", 0.1d, "description2", 1.0d, "book2Author", "New");
        book3 = new Book(3, "book3", 0.1d, "description3", 1.0d, "book3Author", "New");

    }

    CheckoutManagement cm;
    SellBooksDatabase addForSale;

    Book book1;
    Book book2;
    Book book3;

  //  public boolean addSaleBook(int userId, int bookId, String bookCondition, double price) {


      //  @Before
    public void setUp() {
        cm = new CheckoutManagement(Services.getTransactionDatabase());
        book1 = new Book(1, "book1", 0.1d, "description1", 1.0d, "book1Author", "New");
        book2 = new Book(2, "book2", 0.1d, "description2", 1.0d, "book2Author", "New");
        book3 = new Book(3, "book3", 0.1d, "description3", 1.0d, "book3Author", "New");
        }

    private void reset() {
        cm.removeBook(book1);
        cm.removeBook(book2);
        cm.removeBook(book3);
    }

    @Test
    public void buyBookTest(){
        reset();
        assertFalse(cm.buyBook(null));//null test
        assertTrue(cm.buyBook(book1));//add 1 - new true
        assertFalse(cm.buyBook(book1));//add 1 - old false
        assertTrue(cm.buyBook(book2));//add 2 - new true
        assertFalse(cm.buyBook(book2));//add 2 - old false
    }
    @Test
    public void reMoveBookTest(){
        reset();
        assertFalse(cm.removeBook(null));
        cm.buyBook(book1);
        assertFalse(cm.buyBook(book1));
        assertTrue(cm.removeBook(book1));
        assertFalse(cm.removeBook(book1));
        assertTrue(cm.buyBook(book2));
        assertTrue(cm.removeBook(book2));
        assertFalse(cm.removeBook(book2));
    }
    @Test
    public void HistoryTest() throws CheckoutException{


        reset();
        AuthenticatedUser.getInstance().setUser(new User("userName", 1, "password", "nType","nAddress"));
        assertThrows(CheckoutException.class, () -> cm.finishTransaction());
        //need to add also in the book for sale in the database

        cm.buyBook(book1);
        cm.buyBook(book3);
        cm.buyBook(book2);
        addForSale= Services.getSellBooksDatabase();
        addForSale.addSaleBook(book1.getId(),book1.getId(),"new", book1.getPrice());
        addForSale.addSaleBook(book2.getId(),book2.getId(),"new", book2.getPrice());
        addForSale.addSaleBook(book3.getId(),book3.getId(),"new", book3.getPrice());
        cm.finishTransaction();
        List<Transaction> list = cm.pastPurchases();
        assertTrue(list.stream().anyMatch(item -> item.getBook().equals(book2)));
        assertTrue(list.stream().anyMatch(item -> item.getBook().equals(book3)));
        assertTrue(list.stream().anyMatch(item -> item.getBook().equals(book1)));

        assertTrue(cm.isEmpty());
        assertTrue(cm.buyBook(book1));
        assertTrue(cm.buyBook(book2));
        assertTrue(cm.buyBook(book3));


    }
    @After
    public void close(){
        reset();
    }
}
