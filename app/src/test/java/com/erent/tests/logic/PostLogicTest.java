package com.erent.tests.logic;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.erent.objects.Post;
import com.erent.Logic.PostLogic;
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
}
