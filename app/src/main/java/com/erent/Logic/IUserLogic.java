package com.erent.Logic;

import com.erent.objects.User;

public interface IUserLogic
{
    boolean createNewUser(String UserName);
    boolean deleteUser(int ID);
    boolean userAuth(int ID,String UserName);
}
