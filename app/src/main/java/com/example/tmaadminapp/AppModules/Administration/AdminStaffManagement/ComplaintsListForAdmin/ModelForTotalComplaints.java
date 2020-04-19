package com.example.tmaadminapp.AppModules.Administration.AdminStaffManagement.ComplaintsListForAdmin;

import java.util.List;

public class ModelForTotalComplaints
{
    private String title , description , status , field , date;
    private List<String> imageUrl;


    public ModelForTotalComplaints() {
    }

    public ModelForTotalComplaints(String title, String description, String status, String field, String date, List<String> imageUrl) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.field = field;
        this.date = date;
        this.imageUrl = imageUrl;
    }

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
