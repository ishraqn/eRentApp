package com.erent.tests.logic;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.erent.objects.Post;
import com.erent.logic.PostLogic;
import com.erent.persistence.stubs.PostPersistence;

import java.util.List;


public class PostLogicTest {

    @Before
    public void setup()
    {
        System.out.println("Starting test for PostLogic");
    }

    @Test
    public void testCreateNewPost() {
        PostLogic pl = new PostLogic(new PostPersistence());
        List<Post>  allPosts;

        pl.createNewPost("Car", "Winnipeg", "Transport");
        allPosts = pl.getAllPosts();

        assertEquals(6, allPosts.get(5).getPostID());
        assertEquals(6, allPosts.size());

        System.out.println("Finished testCreateNewPost\n");
    }

    @Test
    public void testDeletePost() {
        PostLogic pl = new PostLogic(new PostPersistence());
        List<Post>  allPosts;

        pl.deletePost(1);
        allPosts = pl.getAllPosts();

        assertEquals(4, allPosts.size());

        System.out.println("Finished testDeletePost\n");
    }

    @Test
    public void testGetFirstNPostsEqual() {
        PostLogic pl = new PostLogic(new PostPersistence());
        List<Post> firstNPosts;
        int n = 5;

        firstNPosts = pl.getFirstNPosts(n);

        assertEquals(5, firstNPosts.size());

        System.out.println("Finished testGetFirstNPostsEqual\n");
    }

    @Test
    public void testGetFirstNPostsLess() {
        PostLogic pl = new PostLogic(new PostPersistence());
        List<Post> firstNPosts;
        int n = 4;

        firstNPosts = pl.getFirstNPosts(n);

        assertEquals(4, firstNPosts.size());

        System.out.println("Finished testGetFirstNPostsLess\n");
    }

    @Test
    public void testGetFirstNPostsGreater() {
        PostLogic pl = new PostLogic(new PostPersistence());
        List<Post> firstNPosts;
        int n = 7;

        firstNPosts = pl.getFirstNPosts(n);

        assertEquals(5, firstNPosts.size());

        System.out.println("Finished testGetFirstNPostsGreater\n");
    }

    @Test
    public void testGetPostByID() {
        PostLogic pl = new PostLogic(new PostPersistence());
        int postID = 1;
        Post post = pl.getPostByID(postID);

        assertEquals(postID,post.getPostID());
        assertEquals("Electric Breaker 35 lbs",post.getPostName());
        assertEquals("Brett",post.getPostedBy());
        assertEquals("66 Chancellors Circle, Winnipeg",post.getLocation());
        assertEquals("Construction",post.getCategory());

        System.out.println("Finished testGetPostByID\n");
    }
}
