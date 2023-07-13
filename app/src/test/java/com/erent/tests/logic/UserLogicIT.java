package com.erent.tests.logic;

import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import static org.junit.Assert.*;

import com.erent.logic.UserLogic;
import com.erent.objects.User;
import com.erent.persistence.hsqldb.UserPersistenceHSQLDB;
import com.erent.persistence.stubs.UserPersistence;
import com.erent.tests.utils.TestUtils;

import java.io.IOException;
import java.io.File;

public class UserLogicIT {

    private UserLogic userLogic;
    private File tempDB;

    @Before
    public void setup() throws IOException
    {
        System.out.println("Starting test for User Logic");

        // Create an isolated database for testing
        this.tempDB = TestUtils.copyDB();
        final UserPersistenceHSQLDB persistence = new UserPersistenceHSQLDB(this.tempDB.getAbsolutePath().replace(".script", ""));
        this.userLogic = new UserLogic(persistence);

        // Verify the test database and references now exist
        assertNotNull(userLogic);
    }

    @Test
    public void testCreateUserLogic()
    {
        System.out.println("Starting testCreateUserLogic");

        User user = userLogic.createNewUser("John", "1234");
        assertEquals("John", user.getUsername());

        User secondUser = userLogic.createNewUser("Doe", "1234");
        assertEquals("Doe", secondUser.getUsername());

        System.out.println("Finished testCreateUserLogic\n");
    }

    @Test
    public void testCreateDuplicateUserLogic()
    {
        System.out.println("Starting testDuplicateUserLogic");

        User user = userLogic.createNewUser("John", "1234");
        User userDuplicate = userLogic.createNewUser("John", "1234");
        assertNull(userDuplicate);

        System.out.println("Finished testDuplicateUserLogic\n");
    }

    @Test
    public void testDeleteUserLogic()
    {
        System.out.println("Starting testDeleteUserLogic");

        // Verify that deleting a user works
        User user = userLogic.createNewUser("John", "1234");
        boolean wasDeleted = userLogic.deleteUser(user.getUsername());
        assertTrue(wasDeleted);

        System.out.println("Finished testDeleteUserLogic\n");
    }

    @Test
    public void testDeleteNullUserLogic()
    {
        System.out.println("Starting testDeleteUserLogic");

        // Verify that a user that does not exist will not be deleted
        boolean wasDeleted = userLogic.deleteUser("John");
        assertFalse(wasDeleted);

        System.out.println("Finished testDeleteUserLogic\n");
    }

    @Test
    public void testUserAuthentication()
    {
        System.out.println("Starting testUserAuthentication");

        User user = userLogic.createNewUser("John", "1234");
        boolean toLogin = userLogic.authenticateUser("John", "1234");
        assertTrue(toLogin);

        System.out.println("Finished testUserAuthentication\n");
    }

    @After
    public void tearDown() {
        // reset DB after tests
        this.tempDB.delete();
    }
}

