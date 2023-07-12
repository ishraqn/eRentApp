package com.erent.application;

import com.erent.persistence.IPostPersistence;
import com.erent.persistence.IUserPersistence;
import com.erent.persistence.hsqldb.PostPersistenceHSQLDB;
import com.erent.persistence.hsqldb.UserPersistenceHSQLDB;

public class Services {
    private static IUserPersistence userPersistence = null;
    private static IPostPersistence postPersistence = null;
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
