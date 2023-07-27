package com.erent.logic;

import android.util.Log;

import com.erent.application.Services;
import com.erent.objects.Post;
import com.erent.persistence.IPostPersistence;

import java.util.ArrayList;
import java.util.List;

public class RentalLogic implements IRentalLogic{

    private IPostPersistence postDB;

    public RentalLogic(IPostPersistence postDB)
    {
        this.postDB = postDB;
    }

    @Override
    public List<Integer> getRentedPosts() {
        String currentUser = Services.getCurrentUser();
        List <Post> posts = postDB.getPostList();
        List <Integer> rentedPostsID = new ArrayList<>();

        for(Post post : posts)
        {
            if(currentUser.equals(post.getRentedBy()) && post.getIsRental())
            {
                rentedPostsID.add(post.getPostID());
            }
        }
        return rentedPostsID;
    }

    @Override
    public boolean createRental(int postID) {
        String currentUser = Services.getCurrentUser();
        postDB.setRentalToTrue(postID,currentUser);
        Post post = postDB.getPostByID(postID);

        // Return true if the post was rented and false otherwise
        return post.getIsRental();
    }

    @Override
    public boolean deleteRental(int postID) {
        postDB.setRentalToFalse(postID);
        Post post = postDB.getPostByID(postID);

        // Return true if the post was un-rented and false otherwise
        return post.getIsRental() == false;
    }

    @Override
    public boolean rented(int postID) {
        Post post = postDB.getPostByID(postID);
        return post.getIsRental();
    }
    @Override
    public boolean isUserRenting(Post post, String username) {
        String userRenting = post.getRentedBy();

        if(userRenting == null) {
            return false;
        }

        return userRenting.equals(username);
    }
}
