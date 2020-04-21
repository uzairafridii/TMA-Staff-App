package com.example.tmaadminapp.Views;

import com.example.tmaadminapp.AppModules.Administration.AdminStaffManagement.ComplaintsListForAdmin.ModelForTotalComplaints;

import java.util.List;

public interface AdminCompaintView
{
    // firebase callbacks
    void onGetComplaints(List<ModelForTotalComplaints> complaintsList);


    void showErrorMessage(String message);

    void onHideTextView();

}
