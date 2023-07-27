package com.erent.acceptance;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.erent.R;
import com.erent.application.Services;
import com.erent.persistence.IUserPersistence;
import com.erent.presentation.InitActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class UserAccountManagementTest {
    @Rule
    public ActivityScenarioRule<InitActivity> activityRule = new ActivityScenarioRule<>(InitActivity.class);

    @Before
    public void setup() {
        IUserPersistence userPersistence = Services.getUserPersistence();

        //ensure Person is not in database
        userPersistence.deleteUser("Person");
    }

    @Test
    public void login() {
        //Log in with a username and password
        onView(ViewMatchers.withId(R.id.inputButtonUsername)).perform(typeText("Kaiden"));
        onView(withId(R.id.inputButtonPassword)).perform(typeText("1234"));
        closeSoftKeyboard();
        onView(withId(R.id.buttonLogin)).perform(click());

        //Go to profile page.
        //This also tests viewing the profile
        onView(withId(R.id.layout_profile)).perform(click());

        //Check if logged in user appears on profile
        onView(withId(R.id.name)).check(matches(withText("Kaiden")));
    }

    @Test
    public void createAccount() {
        //Go to sign up page
        onView(withId(R.id.buttonSignUp)).perform(click());

        //Sign up with username and password
        onView(withId(R.id.inputButtonUsername)).perform(typeText("Person"));
        onView(withId(R.id.inputButtonPassword)).perform(typeText("password1"));
        closeSoftKeyboard();
        onView(withId(R.id.buttonSignUp)).perform(click());

        //Log in with username and password
        onView(withId(R.id.inputButtonUsername)).perform(typeText("Person"));
        onView(withId(R.id.inputButtonPassword)).perform(typeText("password1"));
        closeSoftKeyboard();
        onView(withId(R.id.buttonLogin)).perform(click());

        //Go to profile page.
        //This also tests viewing the profile
        onView(withId(R.id.layout_profile)).perform(click());

        //Check if logged in user appears on profile
        onView(withId(R.id.name)).check(matches(withText("Person")));
    }
}
