package com.erent.acceptance;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.erent.R;
import com.erent.application.Services;
import com.erent.presentation.InitActivity;
import com.erent.presentation.MainActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ViewPostTest {
    @Rule
    public ActivityScenarioRule<InitActivity> activityRule = new ActivityScenarioRule<>(InitActivity.class);

    @Test
    public void viewPostOnHomepage() {
        //Log in with the username and password
        onView(ViewMatchers.withId(R.id.inputButtonUsername)).perform(typeText("Kaiden"));
        onView(withId(R.id.inputButtonPassword)).perform(typeText("1234"));
        closeSoftKeyboard();
        onView(withId(R.id.buttonLogin)).perform(click());

        //click on the post
        onView(ViewMatchers.withId(R.id.home_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));

        onView(withId(R.id.title)).check(matches(isDisplayed()));
        onView(withId(R.id.description)).check(matches(isDisplayed()));
        onView(withId(R.id.price)).check(matches(isDisplayed()));
        onView(withId(R.id.rent_period)).check(matches(isDisplayed()));

        //go back
        onView(withId(R.id.buttonBack)).perform(click());
    }

}
