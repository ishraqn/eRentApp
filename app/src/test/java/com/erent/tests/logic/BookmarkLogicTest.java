package com.erent.tests.logic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.erent.application.Services;
import com.erent.logic.BookmarkLogic;
import com.erent.persistence.hsqldb.BookmarkPersistenceHSQLDB;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BookmarkLogicTest {

    private BookmarkLogic bookmarkLogic;
    private BookmarkPersistenceHSQLDB persistence;

    @Before
    public void setup() throws IOException {
        System.out.println("Starting test for Bookmark Logic");

        // Create an mock database for testing
        persistence = mock(BookmarkPersistenceHSQLDB.class);
        this.bookmarkLogic = new BookmarkLogic(persistence);
        Services.setCurrentUser("Brett");

        // Verify the test database and references now exist
        assertNotNull(bookmarkLogic);
    }

    @Test
    public void testGetBookmarksForUserLogic() {
        System.out.println("Starting testGetBookmarksForUserLogic");

        //setup the mock
        List<Integer> bookmarkedPosts = new ArrayList<>();
        bookmarkedPosts.add(1);
        when(persistence.getBookmarkedPostsOfUser(anyString())).thenReturn(bookmarkedPosts);

        //use the mock to test the `getBookmarkedPosts` method
        List<Integer> bookmarkedPostIDs = bookmarkLogic.getBookmarkedPosts();
        assertEquals(bookmarkedPostIDs.size(), 1);

        //verify the mock was used
        verify(persistence).getBookmarkedPostsOfUser(anyString());

        System.out.println("Finished testGetBookmarksForUserLogic\n");
    }

    @Test
    public void testNoBookmarksForUserLogic() {
        System.out.println("Starting testNoBookmarksForUserLogic");

        //setup the mock
        List<Integer> bookmarkedPosts = new ArrayList<>();
        when(persistence.getBookmarkedPostsOfUser(anyString())).thenReturn(bookmarkedPosts);

        //use the mock to test the `getBookmarkedPosts` method
        List<Integer> bookmarkedPostIDs = bookmarkLogic.getBookmarkedPosts();
        assertEquals(bookmarkedPostIDs.size(), 0);

        //verify the mock was used
        verify(persistence).getBookmarkedPostsOfUser(anyString());

        System.out.println("Finished testNoBookmarksForUserLogic\n");
    }

    @Test
    public void testCreateBookmarkLogic() {
        System.out.println("Starting testCreateBookmarkLogic");

        //setup the mock
        when(persistence.createBookmark(anyString(), anyInt())).thenReturn(true);

        boolean success = bookmarkLogic.createBookmark(2);
        assertTrue(success);

        success = bookmarkLogic.createBookmark(4);
        assertTrue(success);

        //verify the mocked method was called twice
        verify(persistence, times(2)).createBookmark(anyString(), anyInt());

        System.out.println("Finished testCreateBookmarkLogic\n");
    }

    @Test
    public void testCreateDuplicateBookmarkLogic() {
        System.out.println("Starting testCreateDuplicateBookmarkLogic");

        //setup the mock
        when(persistence.createBookmark(anyString(), anyInt())).thenReturn(false);

        boolean success = bookmarkLogic.createBookmark(3);
        assertFalse(success);

        //verify the mocked method was called
        verify(persistence).createBookmark(anyString(), anyInt());

        System.out.println("Finished testCreateDuplicateBookmarkLogic\n");
    }

    @Test
    public void testDeleteBookmarkLogic() {
        System.out.println("Starting testDeleteBookmarkLogic");

        //setup the mocks
        when(persistence.createBookmark(anyString(), anyInt())).thenReturn(true);
        when(persistence.deleteBookmark(anyString(), anyInt())).thenReturn(true);

        boolean wasAdded = bookmarkLogic.createBookmark(2);
        assertTrue(wasAdded);
        boolean wasDeleted = bookmarkLogic.deleteBookmark(2);
        assertTrue(wasDeleted);

        //verify the mocked methods were called
        verify(persistence).createBookmark(anyString(), anyInt());
        verify(persistence).deleteBookmark(anyString(), anyInt());

        System.out.println("Finished testDeleteBookmarkLogic\n");
    }

    @Test
    public void testDeleteNullBookmarkLogic() {
        System.out.println("Starting testDeleteNullBookmarkLogic");

        //setup the mock
        when(persistence.deleteBookmark(anyString(), anyInt())).thenReturn(false);

        boolean wasDeleted = bookmarkLogic.deleteBookmark(2);
        assertFalse(wasDeleted);

        //verify the mocked method was called
        verify(persistence).deleteBookmark(anyString(), anyInt());

        System.out.println("Finished testDeleteNullBookmarkLogic\n");
    }

    @Test
    public void testBookmarked() {
        System.out.println("Starting testBookmarked");

        //setup the mock
        List<Integer> bookmarkedPosts = new ArrayList<>();
        bookmarkedPosts.add(1);
        when(persistence.getBookmarkedPostsOfUser(anyString())).thenReturn(bookmarkedPosts);

        boolean bookmarked = bookmarkLogic.bookmarked(1);
        assertTrue(bookmarked);

        //verify the mock was used
        verify(persistence).getBookmarkedPostsOfUser(anyString());

        System.out.println("Finished testBookmarked\n");
    }

    @Test
    public void testBookmarkedFail() {
        System.out.println("Starting testBookmarkedFail");

        //setup the mock
        List<Integer> bookmarkedPosts = new ArrayList<>();
        bookmarkedPosts.add(1);
        when(persistence.getBookmarkedPostsOfUser(anyString())).thenReturn(bookmarkedPosts);

        boolean bookmarked = bookmarkLogic.bookmarked(2);
        assertFalse(bookmarked);

        //verify the mock was used
        verify(persistence).getBookmarkedPostsOfUser(anyString());

        System.out.println("Finished testBookmarkedFail\n");
    }
}
