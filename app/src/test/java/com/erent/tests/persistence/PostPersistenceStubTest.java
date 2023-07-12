package com.erent.tests.persistence;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.erent.objects.Post;
import com.erent.persistence.stubs.PostPersistence;

public class PostPersistenceStubTest {

    @Before
    public void setup()
    {
        System.out.println("Starting test for Post Persistence (Stubbed)");
    }

    @Test
    public void testGetPostByID()
    {
        System.out.println("Starting testGetPostByID");

        PostPersistence postPersistence = new PostPersistence();

        Post post = postPersistence.getPostByID(1);

        assertEquals(1,post.getPostID());
        assertEquals("Electric Breaker 35 lbs",post.getPostName());
        assertEquals("Brett",post.getPostedBy());
        assertEquals("66 Chancellors Circle, Winnipeg",post.getLocation());
        assertEquals("Construction",post.getCategory());

        System.out.println("Finished testGetPostByID\n");
    }

    @Test
    public void testGetPostByPostName()
    {
        System.out.println("Starting testGetPostByPostName");

        PostPersistence postPersistence = new PostPersistence();

        Post post = postPersistence.getPostByPostName("Electric Breaker 35 lbs");

        assertEquals(1,post.getPostID());
        assertEquals("Electric Breaker 35 lbs",post.getPostName());
        assertEquals("Brett",post.getPostedBy());
        assertEquals("66 Chancellors Circle, Winnipeg",post.getLocation());
        assertEquals("Construction",post.getCategory());

        System.out.println("Finished testGetPostByPostName\n");
    }

    @Test
    public void testCreatePost()
    {
        System.out.println("Starting testCreatePost");

        PostPersistence postPersistence = new PostPersistence();

        Post post = postPersistence.createPost("Backlight","Alejandro","Winnipeg","sound");

        assertEquals(6,post.getPostID());
        assertEquals("Backlight",post.getPostName());
        assertEquals("Alejandro",post.getPostedBy());
        assertEquals("Winnipeg",post.getLocation());
        assertEquals("sound",post.getCategory());

        System.out.println("Finished testCreatePost\n");
    }

    @Test
    public void testCreatePostSameName()
    {
        System.out.println("Starting testCreatePost");

        PostPersistence postPersistence = new PostPersistence();

        Post post = postPersistence.createPost("Backlight","Alejandro","Winnipeg","sound");

        assertEquals(6,post.getPostID());
        assertEquals("Backlight",post.getPostName());
        assertEquals("Alejandro",post.getPostedBy());
        assertEquals("Winnipeg",post.getLocation());
        assertEquals("sound",post.getCategory());

        Post post2 = postPersistence.createPost("Backlight","Alejandro","Winnipeg","sound");

        assertEquals(7,post2.getPostID());
        assertEquals("Backlight",post2.getPostName());
        assertEquals("Alejandro",post2.getPostedBy());
        assertEquals("Winnipeg",post2.getLocation());
        assertEquals("sound",post2.getCategory());

        System.out.println("Finished testCreatePost\n");
    }

    @Test
    public void testDeletePost()
    {
        System.out.println("Starting testDeletePost");

        PostPersistence postPersistence = new PostPersistence();

        boolean wasDeleted = postPersistence.deletePost(1);

        assertTrue(wasDeleted);
        assertNull(postPersistence.getPostByPostName("Electric Breaker 35 lbs"));
        assertNull(postPersistence.getPostByID(1));

        System.out.println("Finished testDeletePost\n");
    }

    @Test
    public void testDeletePostNotInDatabase()
    {
        System.out.println("Starting testDeletePostNotInDatabase");

        PostPersistence postPersistence = new PostPersistence();

        boolean wasDeleted = postPersistence.deletePost(6);

        assertFalse(wasDeleted);

        System.out.println("Finished testDeletePostNotInDatabase\n");
    }

}
