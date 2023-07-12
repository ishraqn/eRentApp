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

        Post post = new Post(6,"Microphone", "Brett","Winnipeg","sound");

        assertEquals(6,post.getPostID());
        assertEquals("Microphone",post.getPostName());
        assertEquals("Brett",post.getPostedBy());
        assertEquals("Winnipeg",post.getLocation());
        assertEquals("sound",post.getCategory());
        assertEquals(100, post.getPrice(), 0);
        assertEquals(1, post.getRentDuration(), 0);
        assertEquals("placeholder_post_img", post.getPictureName());

        System.out.println("Finished testConstructPost\n");
    }
}
