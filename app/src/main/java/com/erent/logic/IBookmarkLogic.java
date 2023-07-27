package com.erent.logic;

import java.util.List;

public interface IBookmarkLogic {

    List<Integer> getBookmarkedPosts();

    boolean createBookmark(int postID);

    boolean deleteBookmark(int postID);
    boolean bookmarked(int postID);
}
