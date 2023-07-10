package com.erent.tests.objects;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.erent.objects.User;

public class UserTest
{
    @Before
    public void setup()
    {
        System.out.println("Starting test for User");
    }

    @Test
    public void testConstructUser()
    {
        System.out.println("Starting testConstructUser");

        User user = new User("Foo", "1234");

        assertEquals("Foo", user.getUsername());

        System.out.println("Finished testConstructUser\n");
    }

    @Test
    public void testAuthenticateUser()
    {
        System.out.println("Starting testAuthenticateUser");

        User user = new User("Foo", "1234");

        boolean authentic = user.authenticateUser("1234");

        assertTrue(authentic);

        System.out.println("Finished testAuthenticateUser\n");
    }

    @Test
    public void testAuthenticateUserFail()
    {
        System.out.println("Starting testAuthenticateUserFail");

        User user = new User("Foo", "1234");

        boolean authentic = user.authenticateUser("4321");

        assertFalse(authentic);

        System.out.println("Finished testAuthenticateUserFail\n");
    }
}