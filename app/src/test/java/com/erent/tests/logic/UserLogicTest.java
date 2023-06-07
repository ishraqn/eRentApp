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

        User user = userLogic.createNewUser("John");        //DB has 5 built-in members
        assertEquals(6, user.getUserID());                  //ID is sequential
        assertEquals("John", user.getUsername());

        User user1 = userLogic.createNewUser("Doe");
        assertEquals(7, user1.getUserID());
        assertEquals("Doe", user1.getUsername());

        System.out.println("Finished testCreateUserLogic\n");
    }

    @Test
    public void testDuplicateUserLogic()
    {
        System.out.println("Starting testDuplicateUserLogic");

        UserLogic userLogic = new UserLogic(new UserPersistence());

        User user = userLogic.createNewUser("John");
        User userDuplicate = userLogic.createNewUser("John");
        assertNull(userDuplicate);

        User user1 = userLogic.createNewUser("Ishraq");
        assertNull(user1);

        System.out.println("Finished testDuplicateUserLogic\n");
    }

    @Test
    public void testDeleteUserLogic()
    {
        System.out.println("Starting testDeleteUserLogic");

        UserLogic userLogic = new UserLogic(new UserPersistence());

        User user = userLogic.createNewUser("John");
        boolean wasDeleted = userLogic.deleteUser(user.getUserID());
        assertTrue(wasDeleted);

        boolean wasDeleted2 = userLogic.deleteUser(8);  //List has pre-built 5 members
        assertFalse(wasDeleted2);

        System.out.println("Finished testDeleteUserLogic\n");
    }

    @Test
    public void testUserAuthentication()
    {
        System.out.println("Starting testUserAuthentication");

        UserLogic userLogic = new UserLogic(new UserPersistence());

        User user = userLogic.createNewUser("John");
        boolean toLogin = userLogic.authenticateUser("John");
        assertTrue(toLogin);

        System.out.println("Finished testUserAuthentication\n");
    }
}

