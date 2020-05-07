package com.example.tmaadminapp.AppModules.InfrastructureHead.BuildingNoc;

public class ModelForNoc
{
    private String date , imageUrl , nocTitle , pushKey , uid , status , userName;


    public ModelForNoc() {}


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDate() {
        return date;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getNocTitle() {
        return nocTitle;
    }

    public String getPushKey() {
        return pushKey;
    }

    public String getUid() {
        return uid;
    }

    public String getStatus() {
        return status;
    }
}
