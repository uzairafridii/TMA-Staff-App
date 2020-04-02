package com.example.tmaadminapp.SanitationHead.ComplaintFragements.CompletedComplaints;

public class ModelForCompletedComplaints
{
    private String titleOfPComplaints , statusOfPComplaints , dataAndTimeOfPComplaints;

    public ModelForCompletedComplaints(String titleOfPComplaints, String statusOfPComplaints, String dataAndTimeOfPComplaints) {
        this.titleOfPComplaints = titleOfPComplaints;
        this.statusOfPComplaints = statusOfPComplaints;
        this.dataAndTimeOfPComplaints = dataAndTimeOfPComplaints;
    }

    public String getTitleOfPComplaints() {
        return titleOfPComplaints;
    }

    public void setTitleOfPComplaints(String titleOfPComplaints) {
        this.titleOfPComplaints = titleOfPComplaints;
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
