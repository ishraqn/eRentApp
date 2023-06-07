package com.erent.logic;

import com.erent.objects.Post;

import java.util.List;

public interface IPostLogic
{
    Post createNewPost(String postName,String location, String category);
    public List<Post> getAllPosts();
    public boolean deletePost(int postID);

}
