package com.example.myapplication;

import com.example.myapplication.Models.BookPropertiesTest;
import com.example.myapplication.Models.BookTest;
import com.example.myapplication.Models.UserTest;
import com.example.myapplication.business.unitTests.AccountmanagementTest;
import com.example.myapplication.business.unitTests.BookManagementTest;
import com.example.myapplication.bussinessITtests.AccountManagementTestIT;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
@RunWith(Suite.class)
@Suite.SuiteClasses({
//        AccountmanagementTest.class,
//        BookManagementTest.class,
//        BookTest.class,
//        BookPropertiesTest.class,
//        UserTest.class,
        AccountManagementTestIT.class,
})
public class AllTests {
}
