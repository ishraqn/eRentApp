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

        userList.add(new User("Brett", "1234"));
        userList.add(new User("Alejandro", "1234"));
        userList.add(new User("Kaiden", "1234"));
        userList.add(new User("Shaun", "1234"));
        userList.add(new User("Ishraq", "1234"));
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
    public User createUser(String username, String password)
    {
        /* Do not create a new user if the user already exists */
        if(getUserByUsername(username) != null)
        {
            return null;
        }

        User user = new User(username, password);
        userList.add(user);
        return user;
    }

    @Override
    public boolean deleteUser(String username)
    {
        User userToDelete = getUserByUsername(username);

        return userList.remove(userToDelete);
    }
}
