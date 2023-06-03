package com.erent.persistence;

import com.erent.objects.User;

public interface IUserPersistence
{
    User getUserByID(int userID);

    User getUserByUsername(String username);

    User createUser(String username);

    boolean deleteUser(int userID);
}
