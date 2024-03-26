package com.example.myapplication.bussinessITtests;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import com.example.myapplication.Models.Book;
import com.example.myapplication.Models.Transaction;
import com.example.myapplication.Models.User;
import com.example.myapplication.application.Services;
import com.example.myapplication.business.authentication.AuthenticatedUser;
import com.example.myapplication.business.management.AuthenticationManager;
import com.example.myapplication.business.management.CheckoutManagement;
import com.example.myapplication.business.management.AccountManagement;
import com.example.myapplication.customException.CheckoutException;
import com.example.myapplication.customException.UserCreationException;
import com.example.myapplication.persistence.subinterfaces.SellBooksDatabase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.io.File;
import java.io.IOException;

public class AccountManagementTestIT {

    private File tempDB;
    CheckoutManagement cm;
    SellBooksDatabase addForSale;
    AccountManagement newAccount;
    List<Transaction> pastHistory;
    Book book1;
    Book book2;
    Book book3;

    private static boolean setup = false;

    @Before
    public void setUp() {

        if (!setup) {
            System.out.println("Starting integration test for AccessRecipes");
            try {
                this.tempDB = TestUtils.copyDB();
            } catch (IOException e) {
                System.out.println("Error in starting the test");
                fail();
            }

            cm = new CheckoutManagement(Services.getTransactionDatabase());
            book1 = new Book(1, "book1", 0.1d, "description1", 1.0d, "book1Author", "New");
            book2 = new Book(2, "book2", 0.1d, "description2", 1.0d, "book2Author", "New");
            book3 = new Book(3, "book3", 0.1d, "description3", 1.0d, "book3Author", "New");

            addForSale = Services.getSellBooksDatabase();
            newAccount = new AccountManagement(Services.getUserDatabase());

            addForSale.addSaleBook(book1.getId(), book1.getId(), "new", book1.getPrice());
            addForSale.addSaleBook(book2.getId(), book2.getId(), "new", book2.getPrice());
            addForSale.addSaleBook(book3.getId(), book3.getId(), "new", book3.getPrice());

        } else {

            System.out.println("Setup already executed.");
        }
    }
}