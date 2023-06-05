package com.erent.tests.objects;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.erent.objects.Post;

public class PostTest {

    @Before
    public void setup()
    {
        System.out.println("Starting test for Post");
    }

    @Test
    public void testConstructPost()
    {
        System.out.println("Starting testConstructPost");

        Post post = new Post(6,"Microphone", 1,"Winnipeg","sound");

        assertEquals(6,post.getPostID());
        assertEquals("Microphone",post.getPostName());
        assertEquals(1,post.getPostedBy());
        assertEquals("Winnipeg",post.getLocation());
        assertEquals("sound",post.getCategory());

        System.out.println("Finished testConstructPost\n");
    }
}
