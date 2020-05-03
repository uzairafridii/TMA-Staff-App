package com.example.tmaadminapp.AppModules.Complaints;

import java.util.List;

public class ModelForComplaints
{
    private String title, description, status,
            date, field, uid , name , pushKey;
    private double latitude , longitude;
    private List<String> imageUrl;


    public ModelForComplaints() {}


    public String getPushKey() {
        return pushKey;
    }



    public String getTitle() {
        return title;
    }



    public String getDescription() {
        return description;
    }


    public String getStatus() {
        return status;
    }


    public String getDate() {
        return date;
    }


    public String getField() {
        return field;
    }



    public double getLatitude() {
        return latitude;
    }


    public double getLongitude() {
        return longitude;
    }



    public String getUid() {
        return uid;
    }



    public String getName() {
        return name;
    }

    public List<String> getImageUrl() {
        return imageUrl;
    }


    public void setName(String name)
    {
        this.name  = name;
    }
}
