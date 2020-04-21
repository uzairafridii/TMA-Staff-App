package com.example.tmaadminapp.Models;

import com.example.tmaadminapp.Presenters.AdminStaffManagementPresenter;
import com.example.tmaadminapp.Views.AdminStaffManagementView;

public class AdminStaffManagementPresenterImplementer implements AdminStaffManagementPresenter
{
    private AdminStaffManagementView managementView;

    public AdminStaffManagementPresenterImplementer(AdminStaffManagementView managementView) {
        this.managementView = managementView;
    }

    @Override
    public void workerHead() {
        managementView.onWorkerHead();
    }

    @Override
    public void workerList() {
         managementView.onWorkerList();
    }

    @Override
    public void regulation() {
        managementView.onRegulation();
    }

    @Override
    public void appointAndRetire() {
        managementView.onAppoint();
    }
}
