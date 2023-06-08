package com.erent.Logic;

import com.erent.objects.Post;
import com.erent.persistence.IPostPersistence;

import java.util.List;

public class PostLogic implements IPostLogic{
    private IPostPersistence postDB;

    public PostLogic(IPostPersistence postDB)
    {
        this.postDB = postDB;
    }

    public Post createNewPost(String postName, String location, String category)
    {
        int currentUserID = 1; //would be user id
        return postDB.createPost(postName, currentUserID, location, category);
    }

    public List<Post> getAllPosts()
    {
        return postDB.getPostList();
    }

    public boolean deletePost(int postID)
    {
        return postDB.deletePost(postID);
    }
}
