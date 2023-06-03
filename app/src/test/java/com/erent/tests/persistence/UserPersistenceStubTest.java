package com.erent.tests.persistence;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.erent.objects.User;
import com.erent.persistence.stubs.UserPersistence;

public class UserPersistenceStubTest
{
    @Before
    public void setup()
    {
        System.out.println("Starting test for User Persistence (Stubbed)");
    }

    @Test
    public void testCreateUser()
    {
        System.out.println("Starting testCreateUser");

        UserPersistence userPersistence = new UserPersistence();

        User user = userPersistence.createUser("Foo");

        assertEquals(6, user.getUserID());
        assertEquals("Foo", user.getUsername());

        System.out.println("Finished testCreateUser\n");
    }

    @Test
    public void testCreateUserDuplicate()
    {
        System.out.println("Starting testCreateUserDuplicate");

        UserPersistence userPersistence = new UserPersistence();

        User user = userPersistence.createUser("Brett");

        assertNull(user);

        System.out.println("Finished testCreateUserDuplicate\n");
    }

    @Test
    public void testDeleteUser()
    {
        System.out.println("Starting testDeleteUser");

        UserPersistence userPersistence = new UserPersistence();

        boolean wasDeleted = userPersistence.deleteUser(1);

        assertEquals(true, wasDeleted);
        assertNull(userPersistence.getUserByUsername("Brett"));
        assertNull(userPersistence.getUserByID(1));

        System.out.println("Finished testDeleteUser\n");
    }

    @Test
    public void testDeleteUserNotInDatabase()
    {
        System.out.println("Starting testDeleteUserNotInDatabase");

        UserPersistence userPersistence = new UserPersistence();

        boolean wasDeleted = userPersistence.deleteUser(6);

        assertEquals(false, wasDeleted);

        System.out.println("Finished testDeleteUserNotInDatabase\n");
    }
}
