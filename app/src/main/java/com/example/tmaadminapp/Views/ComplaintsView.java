package com.example.tmaadminapp.Views;

import com.example.tmaadminapp.AppModules.Complaints.ModelForComplaints;

import java.util.List;

public interface ComplaintsView
{

    void onHideTextNoItem();

    void onGetAllSanitationComplaints(List<ModelForComplaints> complaintsList);


}
