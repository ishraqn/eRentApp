package com.erent.logic;

import com.erent.application.Services;
import com.erent.persistence.IBookmarkPersistence;
import com.erent.persistence.IPostPersistence;

import java.util.List;

public class BookmarkLogic implements IBookmarkLogic {

    private IBookmarkPersistence bookmarkDB;

    public BookmarkLogic(IBookmarkPersistence bookmarkDB)
    {
        this.bookmarkDB = bookmarkDB;
    }

    @Override
    public List<Integer> getBookmarkedPosts()
    {
        String currentUsername = Services.getCurrentUser();
        return bookmarkDB.getBookmarkedPostsOfUser(currentUsername);
    }

    @Override
    public boolean createBookmark(int postID)
    {
        String currentUsername = Services.getCurrentUser();
        return bookmarkDB.createBookmark(currentUsername, postID);
    }

    @Override
    public boolean deleteBookmark(int postID)
    {
        String currentUsername = Services.getCurrentUser();
        return bookmarkDB.deleteBookmark(currentUsername, postID);
    }

    @Override
    public boolean bookmarked(int postID) {
        List<Integer> bookmarkedPosts = getBookmarkedPosts();

        for(int i = 0; i < bookmarkedPosts.size(); i++) {
            if(bookmarkedPosts.get(i).intValue() == postID) {
                return true;
            }
        }

        return false;
    }
}
