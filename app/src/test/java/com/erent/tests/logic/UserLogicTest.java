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

    @Test
    public void testUserExits() {
        System.out.println("Starting testUserExits");

        UserLogic userLogic = new UserLogic(new UserPersistence());
        User user = userLogic.createNewUser("John", "1234");
        assertTrue(userLogic.userExists("John"));

        System.out.println("Finished testUserExits\n");
    }

    @Test
    public void testUserExitsFails() {
        System.out.println("Starting testUserExits");

        UserLogic userLogic = new UserLogic(new UserPersistence());
        User user = userLogic.createNewUser("John", "1234");
        assertFalse(userLogic.userExists("Not John"));

        System.out.println("Finished testUserExits\n");
    }

    @Test
    public void testValidPassword() {
        System.out.println("Starting testValidPassword");

        UserLogic userLogic = new UserLogic(new UserPersistence());
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

        UserLogic userLogic = new UserLogic(new UserPersistence());
        String password1 = "1234567";
        String password2 = "superlongpassword";
        String password3 = "short1";

        assertFalse(userLogic.validPassword(password1));
        assertFalse(userLogic.validPassword(password2));
        assertFalse(userLogic.validPassword(password3));

        System.out.println("Finished testValidPasswordFails\n");
    }
}

