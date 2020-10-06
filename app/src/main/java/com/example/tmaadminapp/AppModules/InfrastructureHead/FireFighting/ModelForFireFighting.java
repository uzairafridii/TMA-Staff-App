package com.example.tmaadminapp.AppModules.InfrastructureHead.FireFighting;

public class ModelForFireFighting
{
    private String uid , date , userName;
    private double lat , lng;

    public ModelForFireFighting() {}

    public String getUserName() {
        return userName;
    }


    public String getUid() {
        return uid;
    }

    public String getDate() {
        return date;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }
}
