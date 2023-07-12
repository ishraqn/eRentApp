package com.erent.objects;

public class User
{
    private String username;
    private String password;

    public User(String username, String password)
    {
        this.username = username;
        this.password = password;
    }

    public String getUsername()
    {
        return username;
    }

    public boolean authenticateUser(String password)
    {
        return this.password == password;
    }
}
