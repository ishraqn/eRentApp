package com.erent.objects;

public class User
{
    private int userID;
    private String username;

    public User(int userID, String username)
    {
        this.userID = userID;
        this.username = username;
    }

    public int getUserID()
    {
        return userID;
    }

    public String getUsername()
    {
        return username;
    }
}
