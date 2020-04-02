package com.example.tmaadminapp.AppModules.SanitationHead.ComplaintFragements.PendingComplaints;

public class ModelForPendingComplaints
{
    private String titleOfPComplaints , descriptionOfPComplaints , statusOfPComplaints , dataAndTimeOfPComplaints;

    public ModelForPendingComplaints(String titleOfPComplaints, String descriptionOfPComplaints, String statusOfPComplaints, String dataAndTimeOfPComplaints) {
        this.titleOfPComplaints = titleOfPComplaints;
        this.descriptionOfPComplaints = descriptionOfPComplaints;
        this.statusOfPComplaints = statusOfPComplaints;
        this.dataAndTimeOfPComplaints = dataAndTimeOfPComplaints;
    }

    public String getTitleOfPComplaints() {
        return titleOfPComplaints;
    }

    public void setTitleOfPComplaints(String titleOfPComplaints) {
        this.titleOfPComplaints = titleOfPComplaints;
    }

    public String getDescriptionOfPComplaints() {
        return descriptionOfPComplaints;
    }

    public void setDescriptionOfPComplaints(String descriptionOfPComplaints) {
        this.descriptionOfPComplaints = descriptionOfPComplaints;
    }

    public String getStatusOfPComplaints() {
        return statusOfPComplaints;
    }

    public void setStatusOfPComplaints(String statusOfPComplaints) {
        this.statusOfPComplaints = statusOfPComplaints;
    }

    public String getDataAndTimeOfPComplaints() {
        return dataAndTimeOfPComplaints;
    }

    public void setDataAndTimeOfPComplaints(String dataAndTimeOfPComplaints) {
        this.dataAndTimeOfPComplaints = dataAndTimeOfPComplaints;
    }
}
