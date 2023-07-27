package com.erent.acceptance;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
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
import com.erent.logic.IPostLogic;
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
public class PostManagementTest {
    @Rule
    public ActivityScenarioRule<InitActivity> activityRule = new ActivityScenarioRule<>(InitActivity.class);

    @Before
    public void setup(){
        //create new person to create the post with
        IUserPersistence userPersistence = Services.getUserPersistence();
        userPersistence.createUser("User", "password1");
    }

    @Test
    public void createPost() {
        //Log in with the username and password
        onView(ViewMatchers.withId(R.id.inputButtonUsername)).perform(typeText("User"));
        onView(withId(R.id.inputButtonPassword)).perform(typeText("password1"));
        closeSoftKeyboard();
        onView(withId(R.id.buttonLogin)).perform(click());

        //open create post page
        onView(ViewMatchers.withId(R.id.buttonCreatePost)).perform(click());

        //create post
        onView(withId(R.id.inputButtonTitle)).perform(clearText());
        onView(withId(R.id.inputButtonTitle)).perform(typeText("postTitle"));
        closeSoftKeyboard();
        onView(withId(R.id.inputButtonDescription)).perform(clearText());
        onView(withId(R.id.inputButtonDescription)).perform(typeText("description"));
        closeSoftKeyboard();
        onView(withId(R.id.inputButtonPrice)).perform(clearText());
        onView(withId(R.id.inputButtonPrice)).perform(typeText("100"));
        closeSoftKeyboard();
        onView(withId(R.id.inputButtonDays)).perform(clearText());
        onView(withId(R.id.inputButtonDays)).perform(typeText("7"));
        closeSoftKeyboard();
        onView(withId(R.id.buttonCreatePost)).perform(click());

        //Go to profile page
        onView(withId(R.id.layout_profile)).perform(click());

        //click on the new post
        onView(withId(R.id.recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));

        //verify new post was created properly
        onView(withId(R.id.title)).check(matches(withText("postTitle")));
        onView(withId(R.id.description)).check(matches(withText("description")));
        onView(withId(R.id.price)).check(matches(withText("$100.0")));
        onView(withId(R.id.rent_period)).check(matches(withText("7 Days")));

    }

    @Test
    public void deletePost() {
        //Log in with the username and password
        onView(ViewMatchers.withId(R.id.inputButtonUsername)).perform(typeText("User"));
        onView(withId(R.id.inputButtonPassword)).perform(typeText("password1"));
        closeSoftKeyboard();
        onView(withId(R.id.buttonLogin)).perform(click());

        //ensure there is a post to delete
        IPostLogic postLogic = Services.getPostLogic();
        postLogic.createNewPost("Post Name", "stuff", 300, 1);

        //Go to profile page
        //This also tests viewing the profile
        onView(withId(R.id.layout_profile)).perform(click());

        //click on the post
        onView(withId(R.id.recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));

        //Delete the post
        onView(withId(R.id.buttonDelete)).perform(click());
    }

    @After
    public void teardown() {
        //delete person from the database
        IUserPersistence userPersistence = Services.getUserPersistence();
        userPersistence.deleteUser("User");
    }
}
