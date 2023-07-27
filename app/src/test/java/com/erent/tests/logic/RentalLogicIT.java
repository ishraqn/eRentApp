package com.erent.tests.logic;

import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import static org.junit.Assert.*;

import com.erent.application.Services;
import com.erent.logic.RentalLogic;
import com.erent.logic.UserLogic;
import com.erent.logic.PostLogic;
import com.erent.objects.User;
import com.erent.objects.Post;
import com.erent.persistence.hsqldb.PostPersistenceHSQLDB;
import com.erent.persistence.hsqldb.UserPersistenceHSQLDB;
import com.erent.tests.utils.TestUtils;

import java.io.IOException;
import java.io.File;
import java.util.List;

public class RentalLogicIT {

    private RentalLogic rentalLogic;
    private UserLogic userLogic;
    private PostLogic postLogic;
    private File tempDB;

    @Before
    public void setup() throws IOException
    {
        System.out.println("Starting test for User Logic");

        // Create an isolated database for testing
        this.tempDB = TestUtils.copyDB();
        final PostPersistenceHSQLDB postPersistence = new PostPersistenceHSQLDB(this.tempDB.getAbsolutePath().replace(".script", ""));
        this.rentalLogic = new RentalLogic(postPersistence);
        this.postLogic = new PostLogic(postPersistence);
        final UserPersistenceHSQLDB userPersistence = new UserPersistenceHSQLDB(this.tempDB.getAbsolutePath().replace(".script", ""));
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

        // Setup the user and post for the test
        User user = userLogic.createNewUser("John", "1234");
        assertEquals("John", user.getUsername());

        // The post will be owned by "Brett"
        Services.setCurrentUser("Brett");
        Post post = postLogic.createNewPost("Test", "Test Road", 1f, 1);
        assertEquals("Test", post.getPostName());
        assertEquals("Brett", post.getPostedBy());

        // Switch the logged in user
        Services.setCurrentUser("John");

        postLogic.setRentalToTrue(post.getPostID(), "John");

        // Get the posts rented by the logged in user
        List<Integer> postIDs = rentalLogic.getRentedPosts();

        assertEquals(1, postIDs.size());

        System.out.println("Finished testGetRentedPosts");
    }

    @Test
    public void testGetRentedPostsEmpty()
    {
        System.out.println("Starting testGetRentedPostsEmpty");

        // Setup the user for the test
        User user = userLogic.createNewUser("John", "1234");
        assertEquals("John", user.getUsername());

        // Set the logged in user
        Services.setCurrentUser("John");

        // Get the posts rented by the logged in user
        List<Integer> postIDs = rentalLogic.getRentedPosts();

        assertEquals(0, postIDs.size());

        System.out.println("Finished testGetRentedPostsEmpty");
    }

    @Test
    public void testCreateRental()
    {
        System.out.println("Starting testCreateRental");

        // Setup the user and post for the test
        User user = userLogic.createNewUser("John", "1234");
        assertEquals("John", user.getUsername());

        // The post will be owned by "Brett"
        Services.setCurrentUser("Brett");
        Post post = postLogic.createNewPost("Test", "Test Road", 1f, 1);
        assertEquals("Test", post.getPostName());
        assertEquals("Brett", post.getPostedBy());

        // Switch the logged in user
        Services.setCurrentUser("John");

        boolean success = rentalLogic.createRental(post.getPostID());

        // Get the posts rented by the logged in user
        List<Integer> postIDs = rentalLogic.getRentedPosts();

        assertEquals(1, postIDs.size());
        assertTrue(success);

        System.out.println("Finished testCreateRental");
    }

    @Test
    public void testDeleteRental()
    {
        System.out.println("Starting testDeleteRental");

        // Setup the user and post for the test
        User user = userLogic.createNewUser("John", "1234");
        assertEquals("John", user.getUsername());

        // The post will be owned by "Brett"
        Services.setCurrentUser("Brett");
        Post post = postLogic.createNewPost("Test", "Test Road", 1f, 1);
        assertEquals("Test", post.getPostName());
        assertEquals("Brett", post.getPostedBy());

        // Switch the logged in user
        Services.setCurrentUser("John");

        boolean success = rentalLogic.createRental(post.getPostID());

        // Get the posts rented by the logged in user
        List<Integer> postIDs = rentalLogic.getRentedPosts();

        assertEquals(1, postIDs.size());
        assertTrue(success);

        success = rentalLogic.deleteRental(post.getPostID());

        postIDs = rentalLogic.getRentedPosts();

        assertEquals(0, postIDs.size());
        assertTrue(success);

        System.out.println("Finished testDeleteRental");
    }

    @Test
    public void testRented()
    {
        System.out.println("Starting testRented");

        // Setup the user and post for the test
        User user = userLogic.createNewUser("John", "1234");
        assertEquals("John", user.getUsername());

        // The post will be owned by "Brett"
        Services.setCurrentUser("Brett");
        Post post = postLogic.createNewPost("Test", "Test Road", 1f, 1);
        assertEquals("Test", post.getPostName());
        assertEquals("Brett", post.getPostedBy());

        // Switch the logged in user
        Services.setCurrentUser("John");

        boolean success = rentalLogic.createRental(post.getPostID());
        boolean rented = rentalLogic.rented(post.getPostID());

        // Get the posts rented by the logged in user
        List<Integer> postIDs = rentalLogic.getRentedPosts();

        assertEquals(1, postIDs.size());
        assertTrue(success);
        assertTrue(rented);

        success = rentalLogic.deleteRental(post.getPostID());
        rented = rentalLogic.rented(post.getPostID());

        postIDs = rentalLogic.getRentedPosts();

        assertEquals(0, postIDs.size());
        assertTrue(success);
        assertFalse(rented);

        System.out.println("Finished testRented");
    }

    @Test
    public void testIsUserRenting() {
        Post post = new Post(1, "John", "Test", "Test Road", 1f, 1);
        post.setRentedBy("John");
        post.setIsRentedToTrue();

        boolean renting = rentalLogic.isUserRenting(post, "John");

        assertTrue(renting);
    }

    @Test
    public void testIsUserRentingFail() {
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

