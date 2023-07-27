package com.erent.acceptance;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import android.view.View;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.erent.R;
import com.erent.application.Services;
import com.erent.persistence.IUserPersistence;
import com.erent.presentation.InitActivity;
import com.erent.presentation.MainActivity;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class RentalTest {
    @Rule
    public ActivityScenarioRule<InitActivity> activityRule = new ActivityScenarioRule<>(InitActivity.class);

    @Before
    public void setup(){
        //create new person to bookmark posts for
        IUserPersistence userPersistence = Services.getUserPersistence();
        userPersistence.createUser("User1", "password1");
        userPersistence.createUser("User2", "password1");
    }

    @Test
    public void rent(){
        //Log in with the username and password
        onView(ViewMatchers.withId(R.id.inputButtonUsername)).perform(typeText("User1"));
        onView(withId(R.id.inputButtonPassword)).perform(typeText("password1"));
        closeSoftKeyboard();
        onView(withId(R.id.buttonLogin)).perform(click());

        //click on first post
        onView(ViewMatchers.withId(R.id.home_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));

        //rent the post
        onView(withId(R.id.buttonRent)).perform(click());

        //go back
        onView(withId(R.id.buttonBack)).perform(click());

        //close to log in with different account
        activityRule.getScenario().close();
        ActivityScenario.launch(InitActivity.class);

        //Log in with another username and password
        onView(ViewMatchers.withId(R.id.inputButtonUsername)).perform(typeText("User2"));
        onView(withId(R.id.inputButtonPassword)).perform(typeText("password1"));
        closeSoftKeyboard();
        onView(withId(R.id.buttonLogin)).perform(click());

        //click on first post
        onView(ViewMatchers.withId(R.id.home_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));

        //check that rental button is not there
        onView(withId(R.id.buttonRent)).check(matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.GONE)));

        //return to first account
        activityRule.getScenario().close();
        ActivityScenario.launch(InitActivity.class);

        //Log in with first username and password
        onView(ViewMatchers.withId(R.id.inputButtonUsername)).perform(typeText("User1"));
        onView(withId(R.id.inputButtonPassword)).perform(typeText("password1"));
        closeSoftKeyboard();
        onView(withId(R.id.buttonLogin)).perform(click());

        //Go to rentals
        onView(withId(R.id.layout_rentals)).perform(click());

        //click on the post
        onView(ViewMatchers.withId(R.id.recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));

        //unrent the post
        onView(withId(R.id.buttonRent)).perform(click());

        //go back
        onView(withId(R.id.buttonBack)).perform(click());
    }

    @After
    public void teardown() {
        //delete person from the database
        IUserPersistence userPersistence = Services.getUserPersistence();
        userPersistence.deleteUser("User1");
        userPersistence.deleteUser("User2");
    }
}
