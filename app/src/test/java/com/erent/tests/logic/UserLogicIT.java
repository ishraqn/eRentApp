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

    @Test
    public void testUserAuthenticationNull()
    {
        System.out.println("Starting testUserAuthenticationNull");

        boolean toLogin = userLogic.authenticateUser("John", "1234");
        assertFalse(toLogin);

        System.out.println("Finished testUserAuthenticationNull\n");
    }

    @Test
    public void testUserExists()
    {
        System.out.println("Starting testUserExists");

        User user = userLogic.createNewUser("John", "1234");
        boolean exists = userLogic.userExists("John");
        assertTrue(exists);

        System.out.println("Finished testUserExists\n");
    }

    @Test
    public void testUserExistsFails()
    {
        System.out.println("Starting testUserExists");

        boolean exists = userLogic.userExists("John");
        assertFalse(exists);

        System.out.println("Finished testUserExists\n");
    }

    @Test
    public void testValidPassword() {
        System.out.println("Starting testValidPassword");

        String password1 = "numbers1";
        String password2 = "12345678";
        String password3 = "21jumpstreet!";

        assertTrue(userLogic.validPassword(password1));
        assertTrue(userLogic.validPassword(password2));
        assertTrue(userLogic.validPassword(password3));

        System.out.println("Finished testValidPassword\n");
    }

    @Test
    public void testValidPasswordFails() {
        System.out.println("Starting testValidPasswordFails");

        String password1 = "1234567";
        String password2 = "superlongpassword";
        String password3 = "short1";

        assertFalse(userLogic.validPassword(password1));
        assertFalse(userLogic.validPassword(password2));
        assertFalse(userLogic.validPassword(password3));

        System.out.println("Finished testValidPasswordFails\n");
    }

    @After
    public void tearDown() {
        // reset DB after tests
        this.tempDB.delete();
    }
}

