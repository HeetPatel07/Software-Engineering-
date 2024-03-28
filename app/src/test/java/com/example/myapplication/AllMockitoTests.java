package com.example.myapplication;
import com.example.myapplication.bussinessMockito.*;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
@RunWith(Suite.class)
@Suite.SuiteClasses({
        AuthenticationManager.class,
        BookManagement.class,
        CourseManagement.class,
        FavoriteBooks.class,
        SellBooks.class,
})


public class AllMockitoTests {
}
