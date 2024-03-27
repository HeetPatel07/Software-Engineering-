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

public class BuyBookSystemTest {
    @Rule
    public ActivityScenarioRule homePageActivity  =  new ActivityScenarioRule(HomePageActivity.class);

    @Test
    public void buyBookTest(){
        onView(withId(R.id.profileButton)).perform(click());
        onView(withId(R.id.userNameTextInput)).perform(clearText()).perform(typeText("userone"));
        onView(withId(R.id.passwordTextInput)).perform(clearText()).perform(typeText("123123"));
        closeSoftKeyboard();;
        onView(withId(R.id.loginButton)).perform(click());
        onView(withId(R.id.homeButton)).perform(click());
        onView(withId(R.id.searchInput)).perform(clearText());
        onView(withId(R.id.searchInput)).perform(typeText("1984"));
        closeSoftKeyboard();
        onView(withId(R.id.searchButton)).perform(click());
        onView(withText("view")).perform((click()));
        onView(withId(R.id.buyBookButton)).perform(click());
        onView(withId(R.id.cartButton)).perform(click());
        onView(withId(R.id.finishBuying)).perform(click());
        onView(withText("Confirm")).perform(click());
        onView(withId(R.id.profileButton)).perform(click());
        onView(withId(R.id.viewTransactions)).perform(click());

        onView(withId(R.id.profileButton)).perform(click());
        onView(withId(R.id.logoutButton)).perform(click());
        onView(withText("Logout")).perform(click());
    }
}