package com.erent.logic;

import com.erent.application.Services;
import com.erent.objects.Post;
import com.erent.persistence.IPostPersistence;

import java.util.ArrayList;
import java.util.List;

public class PostLogic implements IPostLogic{
    private IPostPersistence postDB;
    private final String defaultUsername = "Brett";
    public PostLogic(IPostPersistence postDB)
    {
        this.postDB = postDB;
    }

    @Override
    public Post createNewPost(String postName, String description, float price, int rentDuration)
    {
        String currentUsername = Services.getCurrentUser(); //try to get the logged-in user's username
        if(currentUsername == null)
            currentUsername = defaultUsername; //if there is no logged-in user (like in a test), use a default value

        return postDB.createPost(postName, currentUsername, description, price, rentDuration);
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
    public List<Post> getPostByUser(String userName) {
        return postDB.getPostByUser(userName);
    }

    @Override
    public List<Post> getPostNotByUser(String userName) {
        return postDB.getPostNotByUser(userName);
    }

    @Override
    public Post getPostByID(int id) {
        return postDB.getPostByID(id);
    }

    @Override
    public void setRentalToTrue(int postID, String userName)
    {
        postDB.setRentalToTrue(postID,userName);
    }

    @Override
    public void setRentalToFalse(int postID)
    {
        postDB.setRentalToFalse(postID);
    }

    @Override
    public boolean deletePost(int postID)
    {
        return postDB.deletePost(postID);
    }

    @Override
    public List<Post> getPostsFromIDList(List<Integer> postIDs) {
        List<Post> posts = new ArrayList<>();
        Post foundPost = null;

        for(int i = 0; i < postIDs.size(); i++) {
            foundPost = getPostByID(postIDs.get(i).intValue());

            if(foundPost != null) {
                posts.add(foundPost);
            }
        }

        return posts;
    }
}
