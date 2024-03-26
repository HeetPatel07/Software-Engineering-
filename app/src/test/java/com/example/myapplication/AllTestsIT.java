package com.example.myapplication;

import com.example.myapplication.Models.BookPropertiesTest;
import com.example.myapplication.Models.BookTest;
import com.example.myapplication.Models.UserTest;

import com.example.myapplication.bussinessITtests.AccountManagementTestIT;
import com.example.myapplication.bussinessITtests.AuthenticationManagerTestIT;
import com.example.myapplication.bussinessITtests.BookManagementTestIT;
import com.example.myapplication.bussinessITtests.CheckoutManagementTestIT;
//import com.example.myapplication.bussinessITtests.AccountManagementTestIT;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
@RunWith(Suite.class)
@Suite.SuiteClasses({
        AuthenticationManagerTestIT.class,
        BookManagementTestIT.class,
        CheckoutManagementTestIT.class,
        AccountManagementTestIT.class,
})
public class AllTestsIT {
}
