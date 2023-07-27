package com.erent.persistence.stubs;

import com.erent.objects.Post;
import com.erent.persistence.IPostPersistence;

import java.util.*;
import java.util.ArrayList;

public class PostPersistence implements IPostPersistence {

    private List<Post> postList;

    public PostPersistence()
    {
        postList = new ArrayList<>();

        postList.add(new Post(1,"Electric Breaker 35 lbs", "Brett", "66 Chancellors Circle, Winnipeg", 100, 1));
        postList.add(new Post(2,"KRK Rokit5 G3", "Shaun", "21 Jump Street, Vancouver", 100, 1));
        postList.add(new Post(3,"Air Rock Drill 35 lbs", "Alejandro", "22 Jump Street, Winnipeg", 100, 1));
        postList.add(new Post(4,"Shure SM57", "Kaiden", "30 Capilano Road, Vancouver", 100, 1));
        postList.add(new Post(5,"Rode NT1A", "Ishraq", "1845 Pembina Highway, Winnipeg", 100, 1));
    }

    public List<Post> getPostList()
    {
        return postList;
    }

    @Override
    public List<Post> getFirstNPosts(int numberOfPosts)
    {
        int clampedNumberOfPosts = Math.min(postList.size(), numberOfPosts);
        return postList.subList(0, clampedNumberOfPosts);
    }

    @Override
    public List<Post> getPostByUser(String userName) {
        List<Post> list = new ArrayList<>();
        for(int i = 0; i < postList.size();i++)
        {
            if(postList.get(i).getPostedBy().equals(userName))
                list.add(postList.get(i));
        }
        return list;
    }

    @Override
    public List<Post> getPostNotByUser(String userName) {
        List<Post> list = new ArrayList<>();
        for(int i = 0; i < postList.size();i++)
        {
            if(!postList.get(i).getPostedBy().equals(userName))
                list.add(postList.get(i));
        }
        return list;
    }

    @Override
    public Post getPostByID(int postID)
    {
        for(int i = 0; i < postList.size(); i++)
        {
            if(postList.get(i).getPostID() == postID)
            {
                return postList.get(i);
            }
        }

        return null;
    }

    @Override
    public Post getPostByPostName(String postName)
    {
        for(int i = 0; i < postList.size(); i++)
        {
            if(postList.get(i).getPostName().equalsIgnoreCase(postName))
            {
                return postList.get(i);
            }
        }

        return null;
    }

    @Override
    public void setRentalToTrue(int postID, String userName)
    {
        Post post = getPostByID(postID);
        post.setIsRentedToTrue();
        post.setRentedBy(userName);
    }

    @Override
    public void setRentalToFalse(int postID)
    {
        Post post = getPostByID(postID);
        post.setIsRentedToFalse();
        post.setRentedBy(null);
    }

    @Override
    public Post createPost(String postName, String postedBy, String description, float price, int rentDuration)
    {
        Post post = new Post(postList.size() + 1, postName, postedBy, description, price, rentDuration);
        postList.add(post);
        return post;
    }

    @Override
    public boolean deletePost(int postID)
    {
        Post postToDelete = getPostByID(postID);

        return postList.remove(postToDelete);
    }
}
