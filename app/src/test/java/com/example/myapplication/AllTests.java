package com.example.myapplication;

import com.example.myapplication.Models.BookPropertiesTest;
import com.example.myapplication.Models.BookTest;
import com.example.myapplication.Models.UserTest;

import com.example.myapplication.bussinessITtests.BookManagementTestIT;
import com.example.myapplication.bussinessITtests.CheckoutManagenemtTestIT;
//import com.example.myapplication.bussinessITtests.AccountManagementTestIT;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
@RunWith(Suite.class)
@Suite.SuiteClasses({

        BookManagementTestIT.class,
        BookTest.class,
        BookPropertiesTest.class,
        UserTest.class,
      ///  AccountManagementTestIT.class,
        CheckoutManagenemtTestIT.class,
})
public class AllTests {
}
