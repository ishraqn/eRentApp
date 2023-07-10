package com.erent.tests.logic;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.erent.logic.UserLogic;
import com.erent.objects.User;
import com.erent.persistence.stubs.UserPersistence;

public class UserLogicTest {

    @Before
    public void setup()
    {
        System.out.println("Starting test for User Logic");
    }

    @Test
    public void testCreateUserLogic()
    {
        System.out.println("Starting testCreateUserLogic");

        UserLogic userLogic = new UserLogic(new UserPersistence());

        User user = userLogic.createNewUser("John", "1234");
        assertEquals("John", user.getUsername());

        User secondUser = userLogic.createNewUser("Doe", "1234");
        assertEquals("Doe", secondUser.getUsername());

        System.out.println("Finished testCreateUserLogic\n");
    }

    @Test
    public void testDuplicateUserLogic()
    {
        System.out.println("Starting testDuplicateUserLogic");

        UserLogic userLogic = new UserLogic(new UserPersistence());

        User user = userLogic.createNewUser("John", "1234");
        User userDuplicate = userLogic.createNewUser("John", "1234");
        assertNull(userDuplicate);

        System.out.println("Finished testDuplicateUserLogic\n");
    }

    @Test
    public void testDeleteUserLogic()
    {
        System.out.println("Starting testDeleteUserLogic");

        UserLogic userLogic = new UserLogic(new UserPersistence());

        User user = userLogic.createNewUser("John", "1234");
        boolean wasDeleted = userLogic.deleteUser(user.getUsername());
        assertTrue(wasDeleted);

        wasDeleted = userLogic.deleteUser("John");  //List has pre-built 5 members
        assertFalse(wasDeleted);

        System.out.println("Finished testDeleteUserLogic\n");
    }

    @Test
    public void testUserAuthentication()
    {
        System.out.println("Starting testUserAuthentication");

        UserLogic userLogic = new UserLogic(new UserPersistence());

        User user = userLogic.createNewUser("John", "1234");
        boolean toLogin = userLogic.authenticateUser("John", "1234");
        assertTrue(toLogin);

        System.out.println("Finished testUserAuthentication\n");
    }
}

