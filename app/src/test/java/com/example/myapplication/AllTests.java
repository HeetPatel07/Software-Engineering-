package com.example.myapplication;

import com.example.myapplication.Models.BookPropertiesTest;
import com.example.myapplication.Models.BookTest;
import com.example.myapplication.Models.UserTest;

import com.example.myapplication.business.unitTests.*;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({

        BookManagementTest.class,
        BookTest.class,
        BookPropertiesTest.class,
        UserTest.class,
        CheckoutManagementTest.class,
})
public class AllTests {
}
