package com.erent.logic;

import com.erent.objects.Post;
import com.erent.persistence.IPostPersistence;

import java.util.List;

public class PostLogic implements IPostLogic{
    private IPostPersistence postDB;

    public PostLogic(IPostPersistence postDB)
    {
        this.postDB = postDB;
    }

    @Override
    public Post createNewPost(String postName, String location, String category)
    {
        String currentUsername = "Brett"; //would be user's username
        return postDB.createPost(postName, currentUsername, location, category);
    }

    @Override
    public List<Post> getAllPosts()
    {
        return postDB.getPostList();
    }

    @Override
    public List<Post> getFirstNPosts(int n) {
        return postDB.getFirstNPosts(n);
    }

    @Override
    public boolean deletePost(int postID)
    {
        return postDB.deletePost(postID);
    }
}
