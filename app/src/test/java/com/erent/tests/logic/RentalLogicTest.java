package com.erent.tests.logic;

import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.erent.application.Services;
import com.erent.logic.RentalLogic;
import com.erent.logic.UserLogic;
import com.erent.logic.PostLogic;
import com.erent.objects.User;
import com.erent.objects.Post;
import com.erent.persistence.IPostPersistence;
import com.erent.persistence.IUserPersistence;
import com.erent.persistence.hsqldb.PostPersistenceHSQLDB;
import com.erent.persistence.hsqldb.UserPersistenceHSQLDB;
import com.erent.tests.utils.TestUtils;

import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class RentalLogicTest {

    private RentalLogic rentalLogic;
    private UserLogic userLogic;
    private PostLogic postLogic;
    private IPostPersistence postPersistence;
    private IUserPersistence userPersistence;
    private File tempDB;

    @Before
    public void setup() throws IOException
    {
        System.out.println("Starting test for User Logic");

        // Create an isolated database for testing
        this.tempDB = TestUtils.copyDB();
        postPersistence = mock(PostPersistenceHSQLDB.class);
        this.rentalLogic = new RentalLogic(postPersistence);
        this.postLogic = new PostLogic(postPersistence);
        userPersistence = mock(UserPersistenceHSQLDB.class);
        this.userLogic = new UserLogic(userPersistence);

        // Verify the test database and references now exist
        assertNotNull(rentalLogic);
        assertNotNull(postLogic);
        assertNotNull(userLogic);
    }

    @Test
    public void testGetRentedPosts()
    {
        System.out.println("Starting testGetRentedPosts");

        Post post = new Post(1, "John", "Test", "Test Road", 1f, 1);
        post.setRentedBy("John");
        post.setIsRentedToTrue();
        List<Post> posts = new ArrayList<>();
        posts.add(post);

        doNothing().when(postPersistence).setRentalToTrue(anyInt(), anyString());
        when(postPersistence.getPostList()).thenReturn(posts);

        postLogic.setRentalToTrue(post.getPostID(), "John");

        Services.setCurrentUser("John");

        // Get the posts rented by the logged in user
        List<Integer> postIDs = rentalLogic.getRentedPosts();

        assertEquals(1, postIDs.size());

        verify(postPersistence).setRentalToTrue(anyInt(), anyString());
        verify(postPersistence).getPostList();

        System.out.println("Finished testGetRentedPosts");
    }

    @Test
    public void testGetRentedPostsEmpty()
    {
        System.out.println("Starting testGetRentedPostsEmpty");

        List<Post> posts = new ArrayList<>();

        when(postPersistence.getPostList()).thenReturn(posts);

        Services.setCurrentUser("John");

        // Get the posts rented by the logged in user
        List<Integer> postIDs = rentalLogic.getRentedPosts();

        assertEquals(0, postIDs.size());

        verify(postPersistence).getPostList();

        System.out.println("Finished testGetRentedPostsEmpty");
    }

    @Test
    public void testCreateRental()
    {
        System.out.println("Starting testCreateRental");

        // Switch the logged in user
        Services.setCurrentUser("John");

        Post post = new Post(1, "John", "Test", "Test Road", 1f, 1);
        post.setRentedBy("John");
        post.setIsRentedToTrue();
        List<Post> posts = new ArrayList<>();
        posts.add(post);

        doNothing().when(postPersistence).setRentalToTrue(anyInt(), anyString());
        when(postPersistence.getPostList()).thenReturn(posts);
        when(postPersistence.getPostByID(anyInt())).thenReturn(post);

        boolean success = rentalLogic.createRental(post.getPostID());

        // Get the posts rented by the logged in user
        List<Integer> postIDs = rentalLogic.getRentedPosts();

        assertEquals(1, postIDs.size());
        assertTrue(success);

        verify(postPersistence).setRentalToTrue(anyInt(), anyString());
        verify(postPersistence).getPostList();
        verify(postPersistence).getPostByID(anyInt());

        System.out.println("Finished testCreateRental");
    }

    @Test
    public void testDeleteRental()
    {
        System.out.println("Starting testDeleteRental");

        // Switch the logged in user
        Services.setCurrentUser("John");

        Post post = new Post(1, "John", "Test", "Test Road", 1f, 1);
        post.setRentedBy("John");
        post.setIsRentedToTrue();
        List<Post> posts = new ArrayList<>();
        posts.add(post);

        doNothing().when(postPersistence).setRentalToTrue(anyInt(), anyString());
        doNothing().when(postPersistence).setRentalToFalse(anyInt());
        when(postPersistence.getPostList()).thenReturn(posts);
        when(postPersistence.getPostByID(anyInt())).thenReturn(post);

        boolean success = rentalLogic.createRental(post.getPostID());

        // Get the posts rented by the logged in user
        List<Integer> postIDs = rentalLogic.getRentedPosts();

        assertEquals(1, postIDs.size());
        assertTrue(success);

        posts.clear();
        post.setIsRentedToFalse();
        posts.add(post);
        when(postPersistence.getPostList()).thenReturn(posts);
        when(postPersistence.getPostByID(anyInt())).thenReturn(post);

        success = rentalLogic.deleteRental(post.getPostID());

        postIDs = rentalLogic.getRentedPosts();

        assertEquals(0, postIDs.size());
        assertTrue(success);

        verify(postPersistence).setRentalToTrue(anyInt(), anyString());
        verify(postPersistence).setRentalToFalse(anyInt());
        verify(postPersistence, times(2)).getPostList();
        verify(postPersistence, times(2)).getPostByID(anyInt());

        System.out.println("Finished testDeleteRental");
    }

    @Test
    public void testRented()
    {
        System.out.println("Starting testRented");

        // Switch the logged in user
        Services.setCurrentUser("John");

        Post post = new Post(1, "John", "Test", "Test Road", 1f, 1);
        post.setRentedBy("John");
        post.setIsRentedToTrue();
        List<Post> posts = new ArrayList<>();
        posts.add(post);

        doNothing().when(postPersistence).setRentalToTrue(anyInt(), anyString());
        doNothing().when(postPersistence).setRentalToFalse(anyInt());
        when(postPersistence.getPostList()).thenReturn(posts);
        when(postPersistence.getPostByID(anyInt())).thenReturn(post);

        boolean success = rentalLogic.createRental(post.getPostID());
        boolean rented = rentalLogic.rented(post.getPostID());

        // Get the posts rented by the logged in user
        List<Integer> postIDs = rentalLogic.getRentedPosts();

        assertEquals(1, postIDs.size());
        assertTrue(success);
        assertTrue(rented);

        posts.clear();
        post.setIsRentedToFalse();
        posts.add(post);
        when(postPersistence.getPostList()).thenReturn(posts);
        when(postPersistence.getPostByID(anyInt())).thenReturn(post);

        success = rentalLogic.deleteRental(post.getPostID());
        rented = rentalLogic.rented(post.getPostID());

        postIDs = rentalLogic.getRentedPosts();

        assertEquals(0, postIDs.size());
        assertTrue(success);
        assertFalse(rented);

        verify(postPersistence).setRentalToTrue(anyInt(), anyString());
        verify(postPersistence).setRentalToFalse(anyInt());
        verify(postPersistence, times(2)).getPostList();
        verify(postPersistence, times(4)).getPostByID(anyInt());

        System.out.println("Finished testRented");
    }

    @Test
    public void testIsUserRenting() {
        //Doesn't touch the database layer so we Mockito isn't used

        Post post = new Post(1, "John", "Test", "Test Road", 1f, 1);
        post.setRentedBy("John");
        post.setIsRentedToTrue();

        boolean renting = rentalLogic.isUserRenting(post, "John");

        assertTrue(renting);
    }

    @Test
    public void testIsUserRentingFail() {
        //Doesn't touch the database layer so we Mockito isn't used

        Post post1 = new Post(1, "John", "Test", "Test Road", 1f, 1);
        post1.setRentedBy("John");
        post1.setIsRentedToTrue();
        Post post2 = new Post(2, "Not John", "tseT", "Road Test", 2f, 2);

        boolean renting1 = rentalLogic.isUserRenting(post1, "Who?");
        boolean renting2 = rentalLogic.isUserRenting(post2, "Who?");

        assertFalse(renting1);
        assertFalse(renting2);
    }

    @After
    public void tearDown() {
        // reset DB after tests
        this.tempDB.delete();
    }
}

