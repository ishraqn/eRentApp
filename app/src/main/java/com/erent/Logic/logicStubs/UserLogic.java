package com.erent.Logic.logicStubs;

import com.erent.Logic.IUserLogic;
import com.erent.objects.User;
import com.erent.persistence.IUserPersistence;


public class UserLogic implements IUserLogic {
    private IUserPersistence UserDB;

    public UserLogic(IUserPersistence database)
    {
        this.UserDB = database;
    }

    public boolean createNewUser(String UserName)
    {
        User newUser = UserDB.createUser(UserName);
        if(newUser != null)
        {
            System.out.println("Welcome New User " + UserName);
            return true;
        }
        return false;
    }

    public boolean deleteUser(int ID)
    {
        boolean deletedUser = UserDB.deleteUser(ID);
        return deletedUser;
    }

    public boolean userAuth(int ID,String UserName)
    {
        User userID = UserDB.getUserByID(ID);

        if (userID != null && userID.getUsername().equals(UserName))
        {
            return true;
        }
        return false;
    }
}