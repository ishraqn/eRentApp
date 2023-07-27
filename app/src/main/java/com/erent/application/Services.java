package com.erent.application;

import com.erent.logic.BookmarkLogic;
import com.erent.logic.IBookmarkLogic;
import com.erent.logic.IPostLogic;
import com.erent.logic.IRentalLogic;
import com.erent.logic.IUserLogic;
import com.erent.logic.PostLogic;
import com.erent.logic.RentalLogic;
import com.erent.logic.UserLogic;
import com.erent.persistence.IBookmarkPersistence;
import com.erent.persistence.IPostPersistence;
import com.erent.persistence.IUserPersistence;
import com.erent.persistence.hsqldb.BookmarkPersistenceHSQLDB;
import com.erent.persistence.hsqldb.PostPersistenceHSQLDB;
import com.erent.persistence.hsqldb.UserPersistenceHSQLDB;

public class Services {
    private static IUserPersistence userPersistence = null;
    private static IPostPersistence postPersistence = null;
    private static IBookmarkPersistence bookmarkPersistence = null;
    private static IPostLogic postLogic = null;
    private static IUserLogic userLogic = null;
    private static IBookmarkLogic bookmarkLogic = null;
    private static IRentalLogic rentalLogic = null;
    private static String currentUser = null;//Used to specify which user is currently logged in
    //All the static variables for the app is  initialised/changed in this class

    public static synchronized IUserPersistence getUserPersistence()
    {
        if (userPersistence == null)
        {
            userPersistence = new UserPersistenceHSQLDB(Main.getDBPathName());
        }

        return userPersistence;
    }

    public static synchronized IPostPersistence getPostPersistence()
    {
        if (postPersistence == null)
        {
            postPersistence = new PostPersistenceHSQLDB(Main.getDBPathName());
        }

        return postPersistence;
    }

    public static synchronized IBookmarkPersistence getBookmarkPersistence()
    {
        if (bookmarkPersistence == null)
        {
            bookmarkPersistence = new BookmarkPersistenceHSQLDB(Main.getDBPathName());
        }

        return bookmarkPersistence;
    }

    public static synchronized IPostLogic getPostLogic()
    {
        if (postLogic == null)
        {
            postLogic = new PostLogic(getPostPersistence());
        }

        return postLogic;
    }

    public static synchronized IUserLogic getUserLogic()
    {
        if (userLogic == null)
        {
            userLogic = new UserLogic(getUserPersistence());
        }

        return userLogic;
    }

    public static synchronized IBookmarkLogic getBookmarkLogic()
    {
        if (bookmarkLogic == null)
        {
            bookmarkLogic = new BookmarkLogic(getBookmarkPersistence());
        }

        return bookmarkLogic;
    }

    public static synchronized IRentalLogic getRentalLogic()
    {
        if (rentalLogic == null)
        {
            rentalLogic = new RentalLogic(getPostPersistence());
        }

        return rentalLogic;
    }

    public static synchronized void setCurrentUser(String user)
    {
        currentUser = user;
    }

    public static synchronized String getCurrentUser()
    {
        return currentUser;
    }

    public static synchronized void clearCurrentUser()
    {
        currentUser = null;
    }

}
