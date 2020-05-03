package com.example.tmaadminapp.AppModules.WorkersListAndDetails;

public class ModelForWorkersRating
{
    private String comment , user_id , user_rating , image , date, userName;

    public ModelForWorkersRating(String comment, String user_id,
                                 String user_rating, String image, String date, String userName) {
        this.comment = comment;
        this.user_id = user_id;
        this.user_rating = user_rating;
        this.image = image;
        this.date = date;
        this.userName = userName;
    }

    public ModelForWorkersRating() {}


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getComment() {
        return comment;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getUser_rating() {
        return user_rating;
    }

    public String getImage() {
        return image;
    }

    public String getDate() {
        return date;
    }
}
