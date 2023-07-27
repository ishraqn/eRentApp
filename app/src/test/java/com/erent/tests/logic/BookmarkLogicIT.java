package com.erent.tests.logic;

import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import static org.junit.Assert.*;

import com.erent.application.Services;
import com.erent.logic.BookmarkLogic;
import com.erent.persistence.hsqldb.BookmarkPersistenceHSQLDB;
import com.erent.tests.utils.TestUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.File;

public class BookmarkLogicIT {

    private BookmarkLogic bookmarkLogic;
    private File tempDB;

    @Before
    public void setup() throws IOException
    {
        System.out.println("Starting test for Bookmark Logic");

        // Create an isolated database for testing
        this.tempDB = TestUtils.copyDB();
        final BookmarkPersistenceHSQLDB persistence = new BookmarkPersistenceHSQLDB(this.tempDB.getAbsolutePath().replace(".script", ""));
        this.bookmarkLogic = new BookmarkLogic(persistence);
        Services.setCurrentUser("Brett");

        // Verify the test database and references now exist
        assertNotNull(bookmarkLogic);
    }

    @Test
    public void testGetBookmarksForUserLogic()
    {
        System.out.println("Starting testGetBookmarksForUserLogic");

        List<Integer> bookmarkedPostIDs = bookmarkLogic.getBookmarkedPosts();
        assertEquals(bookmarkedPostIDs.size(), 1);

        System.out.println("Finished testGetBookmarksForUserLogic\n");
    }

    @Test
    public void testNoBookmarksForUserLogic()
    {
        System.out.println("Starting testNoBookmarksForUserLogic");

        boolean wasDeleted = bookmarkLogic.deleteBookmark(3);
        assertTrue(wasDeleted);

        List<Integer> bookmarkedPostIDs = bookmarkLogic.getBookmarkedPosts();
        assertEquals(bookmarkedPostIDs.size(), 0);

        System.out.println("Finished testNoBookmarksForUserLogic\n");
    }

    @Test
    public void testCreateBookmarkLogic()
    {
        System.out.println("Starting testCreateBookmarkLogic");

        boolean success = bookmarkLogic.createBookmark(2);
        assertTrue(success);

        success = bookmarkLogic.createBookmark(4);
        assertTrue(success);

        System.out.println("Finished testCreateBookmarkLogic\n");
    }

    @Test
    public void testCreateDuplicateBookmarkLogic()
    {
        System.out.println("Starting testCreateDuplicateBookmarkLogic");

        boolean success = bookmarkLogic.createBookmark(3);
        assertFalse(success);

        System.out.println("Finished testCreateDuplicateBookmarkLogic\n");
    }

    @Test
    public void testDeleteBookmarkLogic()
    {
        System.out.println("Starting testDeleteBookmarkLogic");

        // Verify that deleting a bookmark works
        boolean wasAdded = bookmarkLogic.createBookmark(2);
        assertTrue(wasAdded);
        boolean wasDeleted = bookmarkLogic.deleteBookmark(2);
        assertTrue(wasDeleted);

        System.out.println("Finished testDeleteBookmarkLogic\n");
    }

    @Test
    public void testDeleteNullBookmarkLogic()
    {
        System.out.println("Starting testDeleteNullBookmarkLogic");

        // Verify that a bookmark that does not exist will not be deleted
        boolean wasDeleted = bookmarkLogic.deleteBookmark(2);
        assertFalse(wasDeleted);

        System.out.println("Finished testDeleteNullBookmarkLogic\n");
    }

    @Test
    public void testBookmarked() {
        System.out.println("Starting testBookmarked");

        bookmarkLogic.createBookmark(1);
        boolean bookmarked = bookmarkLogic.bookmarked(1);
        assertTrue(bookmarked);

        System.out.println("Finished testBookmarked\n");
    }

    @Test
    public void testBookmarkedFail() {
        System.out.println("Starting testBookmarkedFail");

        boolean bookmarked = bookmarkLogic.bookmarked(1);
        assertFalse(bookmarked);

        System.out.println("Finished testBookmarkedFail\n");
    }

    @After
    public void tearDown() {
        System.out.println("Ending test for Bookmark Logic");

        // Reset DB after tests
        this.tempDB.delete();
    }
}

