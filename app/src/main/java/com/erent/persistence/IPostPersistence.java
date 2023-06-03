package com.erent.persistence;

import com.erent.objects.Post;

public interface IPostPersistence {

    Post getPostByID(int postID);

    Post getPostByPostName(String postName);

    Post createPost(String postName, int postID, String location, String category);

    boolean deletePost(int postID);
}
