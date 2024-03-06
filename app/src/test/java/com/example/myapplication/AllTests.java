package com.example.myapplication;

import com.example.myapplication.Models.BookPropertiesTest;
import com.example.myapplication.Models.BookTest;
import com.example.myapplication.Models.UserTest;
import com.example.myapplication.business.AccountmanagementTest;
import com.example.myapplication.business.BookManagementTest;
import com.example.myapplication.business.CourseManagementTest;
import com.example.myapplication.business.RatingMangerTest;
import com.example.myapplication.business.SellBooksLogicTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
@RunWith(Suite.class)
@Suite.SuiteClasses({
        AccountmanagementTest.class,
        BookManagementTest.class,
        BookTest.class,
        BookPropertiesTest.class,
        UserTest.class,
        RatingMangerTest.class,
        CourseManagementTest.class,
        SellBooksLogicTest.class
})
public class AllTests {
}
