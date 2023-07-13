package com.erent.tests.logic;

import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import static org.junit.Assert.*;

import com.erent.objects.Post;
import com.erent.logic.PostLogic;
import com.erent.persistence.hsqldb.PostPersistenceHSQLDB;
import com.erent.tests.utils.TestUtils;

import java.io.IOException;
import java.util.List;
import java.io.File;

public class PostLogicIT {

    private PostLogic postLogic;
    private File tempDB;

    @Before
    public void setup() throws IOException
    {
        System.out.println("Starting test for PostLogic");

        // Create an isolated database for testing
        this.tempDB = TestUtils.copyDB();
        final PostPersistenceHSQLDB persistence = new PostPersistenceHSQLDB(this.tempDB.getAbsolutePath().replace(".script", ""));
        this.postLogic = new PostLogic(persistence);

        // Verify the test database and references now exist
        assertNotNull(postLogic);
    }

    @Test
    public void testCreateNewPost() {
        List<Post>  allPosts;

        postLogic.createNewPost("Car", "Winnipeg", "Transport");
        allPosts = postLogic.getAllPosts();

        // Verify the post was created
        assertEquals(6, allPosts.get(5).getPostID());

        // Verify the data of the post is correct
        assertEquals("Winnipeg", allPosts.get(5).getLocation());

        // Verify the post was added to the database
        assertEquals(6, allPosts.size());

        System.out.println("Finished testCreateNewPost\n");
    }

    @Test
    public void testCreateNewPostAfterDeletionIncreasingID() {
        List<Post>  allPosts;

        // Create a post
        postLogic.createNewPost("Car", "Winnipeg", "Transport");
        allPosts = postLogic.getAllPosts();

        // Verify the post was created (with id 6)
        Post newPost1 = allPosts.get(5);
        assertNotNull(newPost1);
        assertEquals(6, newPost1.getPostID());
        assertEquals("Car", newPost1.getPostName());

        // Delete a post
        postLogic.deletePost(1);

        // Create another post
        postLogic.createNewPost("Truck", "Toronto", "Transport");
        allPosts = postLogic.getAllPosts();

        // When a post is created, it's ID is the highest ID in the database plus 1
        // Verify the second post was created (with id 7)
        Post newPost2 = allPosts.get(5);
        assertNotNull(newPost2);
        assertEquals(7, newPost2.getPostID());
        assertEquals("Truck", newPost2.getPostName());

        System.out.println("Finished testCreateNewPost\n");
    }

    @Test
    public void testCreateNewPostAfterDeletionSameID() {
        List<Post>  allPosts;

        // Create a post
        postLogic.createNewPost("Car", "Winnipeg", "Transport");
        allPosts = postLogic.getAllPosts();

        // Verify the post was created (with id 6)
        Post newPost1 = allPosts.get(5);
        assertNotNull(newPost1);
        assertEquals(6, newPost1.getPostID());
        assertEquals("Car", newPost1.getPostName());

        // Delete a post
        postLogic.deletePost(6);

        // Create another post
        postLogic.createNewPost("Truck", "Toronto", "Transport");
        allPosts = postLogic.getAllPosts();

        // Since the highest ID in the post table is 5 (since the post with ID 6 was deleted), the
        // new post should also have the id 6.
        // Verify the second post was created (also with id 6)
        Post newPost2 = allPosts.get(5);
        assertNotNull(newPost2);
        assertEquals(6, newPost2.getPostID());
        assertEquals("Truck", newPost2.getPostName());

        System.out.println("Finished testCreateNewPost\n");
    }

    @Test
    public void testDeletePost() {
        List<Post>  allPosts;

        postLogic.deletePost(1);
        allPosts = postLogic.getAllPosts();

        assertEquals(4, allPosts.size());

        System.out.println("Finished testDeletePost\n");
    }

    @Test
    public void testGetFirstNPostsEqual() {
        List<Post> firstNPosts;
        int n = 5;

        firstNPosts = postLogic.getFirstNPosts(n);

        assertEquals(5, firstNPosts.size());

        System.out.println("Finished testGetFirstNPostsEqual\n");
    }

    @Test
    public void testGetFirstNPostsLess() {
        List<Post> firstNPosts;
        int n = 4;

        firstNPosts = postLogic.getFirstNPosts(n);

        assertEquals(4, firstNPosts.size());

        System.out.println("Finished testGetFirstNPostsLess\n");
    }

    @Test
    public void testGetFirstNPostsGreater() {
        List<Post> firstNPosts;
        int n = 7;

        firstNPosts = postLogic.getFirstNPosts(n);

        assertEquals(5, firstNPosts.size());

        System.out.println("Finished testGetFirstNPostsGreater\n");
    }

    @After
    public void tearDown() {
        // reset DB after tests
        this.tempDB.delete();
    }

}
