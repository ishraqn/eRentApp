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

        postList.add(new Post(1,"Electric Breaker 35 lbs", 1, "66 Chancellors Circle, Winnipeg", "Construction"));
        postList.add(new Post(2,"KRK Rokit5 G3", 4, "21 Jump Street, Vancouver", "Sound"));
        postList.add(new Post(3,"Air Rock Drill 35 lbs", 2, "22 Jump Street, Winnipeg", "Construction"));
        postList.add(new Post(4,"Shure SM57", 3, "30 Capilano Road, Vancouver", "Sound"));
        postList.add(new Post(5,"Rode NT1A", 5, "1845 Pembina Highway, Winnipeg", "Sound"));
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
    public Post createPost(String postName, int postedBy, String location, String category)
    {
        Post post = new Post(postList.size() + 1, postName, postedBy,location, category);
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
