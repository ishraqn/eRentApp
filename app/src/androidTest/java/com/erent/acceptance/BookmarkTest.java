package com.erent.acceptance;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

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

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class BookmarkTest {
    @Rule
    public ActivityScenarioRule<InitActivity> activityRule = new ActivityScenarioRule<>(InitActivity.class);

    @Before
    public void setup(){
        //create new person to bookmark posts for
        IUserPersistence userPersistence = Services.getUserPersistence();
        userPersistence.createUser("User", "password1");
    }

    @Test
    public void bookmark(){
        //Log in with the username and password
        onView(ViewMatchers.withId(R.id.inputButtonUsername)).perform(typeText("User"));
        onView(withId(R.id.inputButtonPassword)).perform(typeText("password1"));
        closeSoftKeyboard();
        onView(withId(R.id.buttonLogin)).perform(click());

        //click on a post
        onView(ViewMatchers.withId(R.id.home_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));

        //bookmark the post
        onView(withId(R.id.buttonBookmark)).perform(click());

        //go back
        onView(withId(R.id.buttonBack)).perform(click());

        //Go to bookmarks
        onView(withId(R.id.layout_bookmarks)).perform(click());

        //click on the post
        onView(ViewMatchers.withId(R.id.recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));

        //unbookmark the post
        onView(withId(R.id.buttonBookmark)).perform(click());

        //go back
        onView(withId(R.id.buttonBack)).perform(click());
    }

    @After
    public void teardown() {
        //delete person from the database
        IUserPersistence userPersistence = Services.getUserPersistence();
        userPersistence.deleteUser("User");
    }
}

