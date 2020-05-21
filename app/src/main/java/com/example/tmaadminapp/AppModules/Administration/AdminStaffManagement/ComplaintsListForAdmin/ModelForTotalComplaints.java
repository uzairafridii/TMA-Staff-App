package com.example.tmaadminapp.AppModules.Administration.AdminStaffManagement.ComplaintsListForAdmin;

import java.util.List;

public class ModelForTotalComplaints
{
    private String title , description , status , field , date , uid , name;
    private List<String> imageUrl;


    public ModelForTotalComplaints() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUid() {
         return uid; }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() { return status; }

    public String getField() {
        return field;
    }

    public String getDate() {
        return date;
    }

    public List<String> getImageUrl() {
        return imageUrl;
    }

}
