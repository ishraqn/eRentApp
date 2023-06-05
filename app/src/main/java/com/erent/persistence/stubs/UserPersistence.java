package com.erent.persistence.stubs;

import com.erent.persistence.IUserPersistence;
import com.erent.objects.User;

import java.util.*;
import java.util.ArrayList;

public class UserPersistence implements IUserPersistence
{
    private List<User> userList;

    public UserPersistence()
    {
        userList = new ArrayList<>();

        userList.add(new User(1, "Brett"));
        userList.add(new User(2, "Alejandro"));
        userList.add(new User(3, "Kaiden"));
        userList.add(new User(4, "Shaun"));
        userList.add(new User(5, "Ishraq"));
    }

    @Override
    public User getUserByID(int userID)
    {
        for(int i = 0; i < userList.size(); i++)
        {
            if(userList.get(i).getUserID() == userID)
            {
                return userList.get(i);
            }
        }

        return null;
    }

    @Override
    public User getUserByUsername(String username)
    {
        for(int i = 0; i < userList.size(); i++)
        {
            if(userList.get(i).getUsername().equals(username))
            {
                return userList.get(i);
            }
        }

        return null;
    }

    @Override
    public User createUser(String username)
    {
        /* Do not create a new user if the user already exists */
        if(getUserByUsername(username) != null)
        {
            return null;
        }

        User user = new User(userList.size() + 1, username);
        userList.add(user);
        return user;
    }

    @Override
    public boolean deleteUser(int userID)
    {
        User userToDelete = getUserByID(userID);

        return userList.remove(userToDelete);
    }
}
