package com.erent.logic;

import com.erent.objects.User;

public interface IUserLogic
{
    User createNewUser(String username);
    boolean deleteUser(int userID);
    boolean authenticateUser(String username);
}
