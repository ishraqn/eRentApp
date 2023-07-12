package com.erent.logic;

import com.erent.objects.User;
import com.erent.persistence.IUserPersistence;

public class UserLogic implements IUserLogic{
    private IUserPersistence userDB;

    public UserLogic(IUserPersistence database)
    {
        this.userDB = database;
    }

    @Override
    public User createNewUser(String username, String password)
    {
        return userDB.createUser(username, password);
    }

    @Override
    public boolean deleteUser(String username)
    {
        return userDB.deleteUser(username);
    }

    @Override
    public boolean authenticateUser(String username, String password)
    {
        return userDB.authenticateUser(username, password);
    }
}