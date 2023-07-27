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
        User user = userDB.getUserByUsername(username);

        // If the user does not exist, it cannot be authentic, so return false
        if(user == null) {
            return false;
        }

        // If the user does exist, compare its password to the passed password and return the result
        return user.getPassword().equals(password);
    }

    @Override
    public boolean userExists(String username) {
        return userDB.getUserByUsername(username) != null;
    }

    @Override
    public boolean validPassword(String password) {
        char[] asArray = password.toCharArray();

        for(int i = 0; i < asArray.length; i++) {
            if(Character.isDigit(asArray[i])) {
                return password.length() > 7;
            }
        }

        return false;
    }
}