package com.example.myapplication;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.example.myapplication.presentation.HomePageActivity;

import org.junit.Rule;
import org.junit.Test;

public class RatingsAndCommentsSystemTest {
    @Rule
    public ActivityScenarioRule homePageActivity  =  new ActivityScenarioRule(HomePageActivity.class);

    @Test
    public void testView(){
        onView(withId(R.id.searchInput)).perform(typeText("bio"));
        closeSoftKeyboard();
        onView(withId(R.id.searchButton)).perform(click());
        onView(withText("view")).perform((click()));

        // Verify the first comment's username and rating (modify these IDs and texts based on actual data or setup)
        onView(withId(R.id.userNameComment)).check(matches(withText("Username: userone")));
        onView(withId(R.id.userRating)).check(matches(withText("Rating: 4 / 5")));
        onView(withId(R.id.userComment)).check(matches(withText("Comment: A comprehensive textbook that covers essential topics in cell biology.")));

    }
}