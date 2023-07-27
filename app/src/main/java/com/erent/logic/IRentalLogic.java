package com.erent.logic;
import com.erent.objects.Post;

import java.util.List;

public interface IRentalLogic {
    List <Integer> getRentedPosts();

    boolean createRental(int postID);
    boolean deleteRental(int postID);
    boolean rented(int postID);
    boolean isUserRenting(Post post, String username);
}
