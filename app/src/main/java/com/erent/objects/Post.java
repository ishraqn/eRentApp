package com.erent.objects;

public class Post {

    private int postID;
    private String postName;
    private String postedBy;
    private String location;
    private String category;
    private String description;
    private float price;        // in USD
    private int rentDuration;   // in days, time the item can be rented out for
    private String pictureName;
    private String isRented;
    private String rentedBy;

    // Default values
    private final String defaultLocation = "22 Jump Street";
    private final String defaultCategory = "Construction";
    private final float defaultPrice = 100.0f;
    private final int defaultRentDuration = 1;
    private final String defaultPictureName = "placeholder_post_img";
    private final String defaultIsRented = "No";
    private final String defaultRentedBy = "None";

    public Post (int postID, String postName, String postedBy, String description, float price, int rentDuration)
    {
        this.postID = postID;
        this.postName = postName;
        this.postedBy = postedBy;
        this.location = defaultLocation;
        this.category = defaultCategory;
        this.description = description;
        this.price = price;
        this.rentDuration = rentDuration;
        this.pictureName = defaultPictureName;
        this.isRented = defaultIsRented;
        this.rentedBy = defaultRentedBy;
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

    public Boolean getIsRental()
    {
        Boolean result = false;
        if(this.isRented.equals("Yes"))
            result = true;
        return result;
    }

    public String getRentedBy()
    {
        return this.rentedBy;
    }

    public String getDescription(){return this.description;}

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

    public void setIsRentedToTrue()
    {
        this.isRented = "Yes";
    }

    public void setIsRentedToFalse()
    {
        this.isRented = "No";
    }

    public void setRentedBy(String user)
    {
        this.rentedBy = user;
    }

}
