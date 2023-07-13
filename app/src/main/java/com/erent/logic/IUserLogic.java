package com.erent.logic;

import com.erent.objects.User;

public interface IUserLogic
{
    User createNewUser(String username, String password);
    boolean deleteUser(String username);
    boolean authenticateUser(String username, String password);
    boolean userExists(String username);
}
