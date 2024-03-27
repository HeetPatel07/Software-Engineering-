package com.example.myapplication;

import  androidx.test.espresso.Espresso;

import android.content.Context;
import android.view.View;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.FailureHandler;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import static org.hamcrest.Matchers.allOf;

import com.example.myapplication.persistence.utils.DBHelper;
import com.example.myapplication.presentation.HomePageActivity;
import com.example.myapplication.presentation.LoginActivity;


import java.io.File;
import java.io.IOException;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Before
    public void load(){
        DBHelper.resetDB(InstrumentationRegistry.getInstrumentation().getTargetContext());
        ActivityScenario.launch(LoginActivity.class);

    }

    @Test
    public void loginUser() {


        Espresso.onView(ViewMatchers.withId(R.id.userNameTextInput))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));


        Espresso.onView(ViewMatchers.withId(R.id.userNameTextInput))
                .perform(ViewActions.typeText("userone"));

        Espresso.onView(ViewMatchers.withId(R.id.passwordTextInput))
                .perform(ViewActions.typeText("123123"),ViewActions.closeSoftKeyboard());

        Espresso.onView(ViewMatchers.withId(R.id.loginButton)).perform(ViewActions.click());

    }

    @Test
    public void createNewUser() throws InterruptedException {
        Espresso.onView(ViewMatchers.withId(R.id.create_account_button)).perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.enter_username_field))
                .perform(ViewActions.typeText("userthree"),ViewActions.closeSoftKeyboard());

        Espresso.onView(ViewMatchers.withId(R.id.enter_address_field))
                .perform(ViewActions.typeText("R3T"),ViewActions.closeSoftKeyboard());

        Espresso.onView(ViewMatchers.withId(R.id.enter_password_field))
                .perform(ViewActions.typeText("123123"),
                        ViewActions.closeSoftKeyboard(),
                        ViewActions.swipeUp(),
                        ViewActions.swipeUp(),
                        ViewActions.swipeUp());


        Espresso.onView(ViewMatchers.withId(R.id.account_confirm)).perform(ViewActions.click());
    }

}