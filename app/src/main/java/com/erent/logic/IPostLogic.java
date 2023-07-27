package com.erent.logic;

import com.erent.objects.Post;

import java.util.List;

public interface IPostLogic
{
    Post createNewPost(String postName, String description, float price, int rentDuration);
    List<Post> getAllPosts();
    List<Post> getFirstNPosts(int n);
    List<Post> getPostByUser(String userName);
    List<Post> getPostNotByUser(String userName);
    Post getPostByID(int id);
    void setRentalToTrue(int postID, String userName);
    void setRentalToFalse(int postID);
    boolean deletePost(int postID);
    public List<Post> getPostsFromIDList(List<Integer> postIDs);

}
