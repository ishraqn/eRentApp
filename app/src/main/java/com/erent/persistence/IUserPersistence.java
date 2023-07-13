package com.erent.persistence;

import com.erent.objects.User;

public interface IUserPersistence
{
    User getUserByUsername(String username);

    User createUser(String username, String password);

    boolean deleteUser(String username);

}
