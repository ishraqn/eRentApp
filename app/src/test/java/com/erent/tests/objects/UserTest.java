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

        User user = new User(1, "Foo");

        assertEquals(1, user.getUserID());
        assertEquals("Foo", user.getUsername());

        System.out.println("Finished testConstructUser\n");
    }
}