package com.example.myapplication;



import org.junit.runner.RunWith;
import org.junit.runners.Suite;



@RunWith(Suite.class)
@Suite.SuiteClasses({
        BuyBookSystemTest.class,
        SearchBookSystemTest.class,
        ViewBookSystemTest.class,
        FavouriteBookSystemTest.class,
        RatingsAndCommentsSystemTest.class,
        AccountSystemTest.class,
})
public class AllSystemTests {
}

