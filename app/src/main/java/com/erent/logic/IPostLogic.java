package com.erent.logic;

import com.erent.objects.Post;

import java.util.List;

public interface IPostLogic
{
    Post createNewPost(String postName,String location, String category);
    List<Post> getAllPosts();
    List<Post> getFirstNPosts(int n);

    Post getPostByID(int id);
    boolean deletePost(int postID);

}
