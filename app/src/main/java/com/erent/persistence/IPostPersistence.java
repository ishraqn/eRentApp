package com.erent.persistence;

import com.erent.objects.Post;

import java.util.List;

public interface IPostPersistence {

    List<Post> getPostList();

    List<Post> getFirstNPosts(int numberOfPosts);

    List<Post> getPostByUser(String userName);

    List<Post> getPostNotByUser(String userName);

    Post getPostByID(int postID);

    Post getPostByPostName(String postName);

    void setRentalToTrue(int postID, String userName);

    void setRentalToFalse(int postID);

    Post createPost(String postName, String postedBy, String description, float price, int rentDuration);

    boolean deletePost(int postID);
}
