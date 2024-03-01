package com.example.myapplication;

import com.example.myapplication.Models.BookPropertiesTest;
import com.example.myapplication.Models.BookTest;
import com.example.myapplication.Models.UserTest;
import com.example.myapplication.business.BookManagementTest;
import com.example.myapplication.business.RatingMangerTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
@RunWith(Suite.class)
@Suite.SuiteClasses({
        BookTest.class,
        BookPropertiesTest.class,
        UserTest.class,
        BookManagementTest.class,
        RatingMangerTest.class
})
public class AllTests {
}
