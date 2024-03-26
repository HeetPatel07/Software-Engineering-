package com.example.myapplication.acceptancetest;

import androidx.test.core.app.ActivityScenario;

import com.example.myapplication.R;
import com.example.myapplication.presentation.HomePageActivity;
import com.example.myapplication.presentation.LoginActivity;


import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import android.os.StrictMode;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.action.ViewActions;
import androidx.test.runner.AndroidJUnit4;


import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class UITest {

    @Before
    public void homePage(){
        ActivityScenario.launch(HomePageActivity.class);
        onView(withId(R.id.searchButton)).check(matches(isDisplayed()));

    }


    @Test
    public void test(){
        onView(withId(R.id.searchInput)).perform(typeText("moby"));
        onView(withId(R.id.searchButton)).perform(click());

    }

}
