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

public class ViewBookSystemTest {
    @Rule
    public ActivityScenarioRule homePageActivity  =  new ActivityScenarioRule(HomePageActivity.class);

    @Test
    public void testViewBookInfo(){
        onView(withId(R.id.searchInput)).perform(typeText("The Great"));
        closeSoftKeyboard();
        onView(withId(R.id.searchButton)).perform(click());
        onView(withText("view")).perform((click()));

        onView(withId(R.id.bookName)).check(matches(withText("The Great Gatsby")));
        onView(withId(R.id.bookAuthor)).check(matches(withText("F. Scott Fitzgerald")));
        onView(withId(R.id.bookPrice)).check(matches(withText("Price: $15.00")));
        onView(withId(R.id.bookEdition)).check(matches(withText("1.00")));
        onView(withId(R.id.bookDescription)).check(matches(withText("A classic novel of American literature set in the Roaring Twenties.")));
        pressBack();
        onView(withId(R.id.searchInput)).perform(clearText());

    }
}