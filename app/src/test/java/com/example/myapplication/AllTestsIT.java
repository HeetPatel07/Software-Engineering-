package com.example.myapplication;

import com.example.myapplication.Models.BookPropertiesTest;
import com.example.myapplication.Models.BookTest;
import com.example.myapplication.Models.UserTest;

import com.example.myapplication.bussinessITtests.AccountManagementTestIT;
import com.example.myapplication.bussinessITtests.AuthenticationManagerTestIT;
import com.example.myapplication.bussinessITtests.BookManagementTestIT;
import com.example.myapplication.bussinessITtests.CheckoutManagementTestIT;
import com.example.myapplication.bussinessITtests.CourseMangementTestIT;
import com.example.myapplication.bussinessITtests.FavouriteBookManagementTestIT;
import com.example.myapplication.bussinessITtests.SellBooksManagementTestIT;
//import com.example.myapplication.bussinessITtests.AccountManagementTestIT;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
@RunWith(Suite.class)
@Suite.SuiteClasses({
        AuthenticationManagerTestIT.class,
        BookManagementTestIT.class,
        CheckoutManagementTestIT.class,
        AccountManagementTestIT.class,
        CourseMangementTestIT.class,
        FavouriteBookManagementTestIT.class,
        SellBooksManagementTestIT.class,
})
public class AllTestsIT {
}
