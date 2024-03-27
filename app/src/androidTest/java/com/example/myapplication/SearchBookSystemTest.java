package com.example.myapplication;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.example.myapplication.presentation.HomePageActivity;

import org.junit.Rule;
import org.junit.Test;

public class SearchBookSystemTest {
    @Rule
    public ActivityScenarioRule homePageActivity  =  new ActivityScenarioRule(HomePageActivity.class);

    @Test
    public void testSearch(){
        onView(withId(R.id.searchInput)).perform(typeText("Great"));
        closeSoftKeyboard();
        onView(withId(R.id.searchButton)).perform(click());
        onView(withText("view")).perform((click()));
        pressBack();


    }
}