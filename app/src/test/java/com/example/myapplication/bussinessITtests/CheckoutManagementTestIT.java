package com.example.myapplication.bussinessITtests;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import com.example.myapplication.Models.Book;
import com.example.myapplication.Models.Transaction;
import com.example.myapplication.Models.User;
import com.example.myapplication.application.Services;
import com.example.myapplication.business.authentication.Authenticate;
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


public class CheckoutManagementTestIT {

    //Local variables used for testing
    private File tempDB;
    CheckoutManagement cm;
    SellBooksDatabase addForSale;
    AccountManagement newAccount;
    List<Transaction> pastHistory;
    Book book1;
    Book book2;
    Book book3;


    @Before
    public void setup() {

        try {
            this.tempDB = TestUtils.copyDB();
        } catch (IOException e) {
            System.out.println("Error starting the test");
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
    }

    private void reset() {
        cm.removeBook(book1);
        cm.removeBook(book2);
        cm.removeBook(book3);
        assertTrue(cm.isEmpty());
    }


    @Test
    public void HistoryTest() throws CheckoutException {
        reset();

        AuthenticatedUser.getInstance().setUser(new User("userName", 1, "password", "nType", "nAddress"));
        assertThrows(CheckoutException.class, () -> cm.finishTransaction());
        //need to add also in the book for sale in the database

        cm.buyBook(book1);
        cm.buyBook(book3);
        cm.buyBook(book2);

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

    private List<Transaction> getList() {

        List<Transaction> result = null;
        try {
            result = cm.pastPurchases();
        } catch (CheckoutException e) {
            System.out.println("The authenticated user is null");

        }
        return result;
    }

    @Test
    public void oldUserPastPurchases() {

        AccountManagement newAccount = new AccountManagement(Services.getUserDatabase());    //creating account
        Authenticate manager = new AuthenticationManager(Services.getUserDatabase());   //logging in the user

        AuthenticatedUser.getInstance().setUser(new User("userName", 1, "password", "nType", "nAddress"));

        pastHistory = getList();     //the already existing user in the database
        assertFalse(pastHistory.isEmpty());
        newAccount.logoutUser();    //logging out the user and making a new user
    }

    @Test
    public void newUserPastPurchases() {

        AuthenticationManager manager = new AuthenticationManager(Services.getUserDatabase());   //logging in the user

        try {
            //the list for this user should be empty
            newAccount.createNewUser("IntegrationTest", "Sample", "Student", "University");
            manager.authenticateUser("IntegrationTest", "Sample");
        } catch (UserCreationException e) {
            System.out.println("The test should fail if a exception is thrown");
            fail("Second test in the function pastPurchases() failed");
        }

        pastHistory = getList();
        assertTrue(pastHistory.isEmpty());
    }

    @After
    public void finish() {
        if (AuthenticatedUser.getInstance().getUser() != null)
            newAccount.logoutUser();    //logging out the user
        reset();
    }
}
