package com.example.myapplication;

import com.example.myapplication.Models.BookPropertiesTest;
import com.example.myapplication.Models.BookTest;
import com.example.myapplication.Models.RatingTest;
import com.example.myapplication.Models.UserTest;
import com.example.myapplication.Models.Book;
import com.example.myapplication.business.BookManagementTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
@RunWith(Suite.class)
@Suite.SuiteClasses({
        BookTest.class,
        BookPropertiesTest.class,
        RatingTest.class,
        UserTest.class,
        BookManagementTest.class
})
public class AllTests {
}
