package com.example.myapplication;

import androidx.test.core.app.ActivityScenario;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.myapplication.persistence.utils.DBHelper;
import com.example.myapplication.presentation.LoginActivity;

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

