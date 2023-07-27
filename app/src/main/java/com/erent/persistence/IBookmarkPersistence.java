package com.erent.persistence;

import java.util.List;

public interface IBookmarkPersistence {

    List<Integer> getBookmarkedPostsOfUser(String username);

    boolean createBookmark(String username, int postID);

    boolean deleteBookmark(String username, int postID);
}
