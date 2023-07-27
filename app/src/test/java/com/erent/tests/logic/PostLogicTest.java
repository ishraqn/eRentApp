package com.erent.tests.logic;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.erent.objects.Post;
import com.erent.logic.PostLogic;
import com.erent.persistence.hsqldb.PostPersistenceHSQLDB;
import com.erent.persistence.stubs.PostPersistence;

import java.util.ArrayList;
import java.util.List;


public class PostLogicTest {

    private PostLogic postLogic;
    private PostPersistenceHSQLDB persistence;

    @Before
    public void setup()
    {
        System.out.println("Starting test for PostLogic");

        // Create an mock database for testing
        persistence = mock(PostPersistenceHSQLDB.class);
        this.postLogic = new PostLogic(persistence);

        // Verify the test database and references now exist
        assertNotNull(postLogic);
    }

    @Test
    public void testCreateNewPost() {
        PostLogic pl = new PostLogic(new PostPersistence());
        List<Post>  allPosts;

        pl.createNewPost("Car", "It's a car", 100, 1);
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

        System.out.println("Finished testGetPostByID\n");
    }

    @Test
    public void testGetPostByUser()
    {
        //setup the mock
        List<Post> list = new ArrayList<>();
        list.add(new Post(1,"Drill","Shaun", "Winnipeg", 100, 1));

        when(persistence.getPostByUser(anyString())).thenReturn(list);

        //use the mock to test the 'getPostByUser' method
        List<Post> postList = postLogic.getPostByUser(anyString());
        assertEquals(postList.size(),1);

        //verify the mock was used
        verify(persistence).getPostByUser(anyString());

        System.out.println("Finished testGetPostByUser\n");
    }

    @Test
    public void testGetPostNotByUser()
    {
        //setup the mock
        List<Post> list = new ArrayList<>();
        list.add(new Post(1,"Drill","Shaun", "Winnipeg", 100, 1));

        when(persistence.getPostByUser(anyString())).thenReturn(list);

        //use the mock to test the 'getPostByUser' method
        List<Post> postList = postLogic.getPostByUser(anyString());
        assertEquals(postList.size(),1);

        //verify the mock was used
        verify(persistence).getPostByUser(anyString());

        System.out.println("Finished testGetPostByUser\n");
    }

    @Test
    public void testSetRentalToTrue()
    {
        postLogic.setRentalToTrue(anyInt(), anyString());

        verify(persistence).setRentalToTrue(anyInt(), anyString());
    }

    @Test
    public void testSetRentalToFalse()
    {
        postLogic.setRentalToFalse(anyInt());

        verify(persistence).setRentalToFalse(anyInt());
    }

    @Test
    public void testGetPostsFromIDList() {
        List<Integer> postIDS = new ArrayList<>();
        postIDS.add(1);
        postIDS.add(2);
        postIDS.add(3);

        //setup the mock
        Post post = new Post(1,"KRK Rokit5 G3","Brett", "Winnipeg", 100, 1);

        when(persistence.getPostByID(anyInt())).thenReturn(post);

        List<Post> posts = postLogic.getPostsFromIDList(postIDS);
        assertEquals(3, posts.size());

        verify(persistence,times(3)).getPostByID(anyInt());

        System.out.println("Finished testGetPostsFromIDList\n");
    }

    @Test
    public void testGetPostsFromIDListEmpty() {
        List<Integer> postIDS = new ArrayList<>();

        //setup the mock
        Post post = new Post(1,"KRK Rokit5 G3","Brett", "Winnipeg", 100, 1);

        when(persistence.getPostByID(anyInt())).thenReturn(post);

        List<Post> posts = postLogic.getPostsFromIDList(postIDS);
        assertEquals(0, posts.size());

        // Gets called 0 times since we are providing an empty list
        verify(persistence,times(0)).getPostByID(anyInt());

        System.out.println("Finished testGetPostsFromIDList\n");
    }

    @Test
    public void testGetPostsFromIDListNull() {
        List<Integer> postIDS = new ArrayList<>();
        postIDS.add(1);

        //setup the mock
        Post post = null;

        when(persistence.getPostByID(anyInt())).thenReturn(post);

        List<Post> posts = postLogic.getPostsFromIDList(postIDS);
        assertEquals(0, posts.size());

        verify(persistence).getPostByID(anyInt());

        System.out.println("Finished testGetPostsFromIDList\n");
    }
}
