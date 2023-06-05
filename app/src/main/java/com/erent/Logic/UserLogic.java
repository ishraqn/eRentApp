package com.erent.logic;

import com.erent.objects.User;
import com.erent.persistence.IUserPersistence;

public class UserLogic implements IUserLogic{
    private IUserPersistence userDB;

    public UserLogic(IUserPersistence database)
    {
        this.userDB = database;
    }

    public User createNewUser(String username)
    {
        return userDB.createUser(username);
    }

    public boolean deleteUser(int userID)
    {
        return userDB.deleteUser(userID);
    }

    public boolean authenticateUser(int userID,String username)
    {
        User user = userDB.getUserByID(userID);
        
        return user.getUsername().equals(username);
    }
}