package com.erent.objects;

public class Post {

    private int postID;
    private String postName;
    private int postedBy;
    private String location;
    private String category;

    public Post (int postID, String postName, int postedBy, String location, String category)
    {
        this.postID = postID;
        this.postName = postName;
        this.postedBy = postedBy;
        this.location = location;
        this.category = category;
    }

    public int getPostID()
    {
        return postID;
    }

    public String getPostName()
    {
        return postName;
    }

    public int getPostedBy()
    {
        return postedBy;
    }

    public String getLocation()
    {
        return location;
    }

    public String getCategory()
    {
        return category;
    }

    public void setPostName(String postName)
    {
        this.postName = postName;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    public void setCategory(String category)
    {
        this.category = category;
    }

}
