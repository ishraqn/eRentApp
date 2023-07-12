package com.erent.persistence;

import com.erent.objects.Post;

import java.util.List;

public interface IPostPersistence {

    List<Post> getPostList();

    List<Post> getFirstNPosts(int numberOfPosts);

    Post getPostByID(int postID);

    Post getPostByPostName(String postName);

    Post createPost(String postName, String postedBy, String location, String category);

    boolean deletePost(int postID);
}
