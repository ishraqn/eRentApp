package com.erent.objects;

public class Post {

    private int postID;
    private String postName;
    private String postedBy;
    private String location;
    private String category;
    private float price;        // in USD
    private int rentDuration;   // in days, time the item can be rented out for
    private String pictureName;

    public Post (int postID, String postName, String postedBy, String location, String category)
    {
        this.postID = postID;
        this.postName = postName;
        this.postedBy = postedBy;
        this.location = location;
        this.category = category;
        this.price = 100;
        this.rentDuration = 1;
        this.pictureName = "placeholder_post_img";
    }

    public int getPostID()
    {
        return postID;
    }

    public String getPostName()
    {
        return postName;
    }

    public String getPostedBy()
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

    public float getPrice() {
        return price;
    }

    public int getRentDuration() {
        return rentDuration;
    }

    public String getPictureName() {
        return pictureName;
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
