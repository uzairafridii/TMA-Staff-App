package com.example.tmaadminapp.AppModules.Administration.AdminStaffManagement.StaffManagementHome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.tmaadminapp.AppModules.Administration.AdminStaffManagement.AppointAndRetireInStaffManagement.AppointAndRetireDetails;
import com.example.tmaadminapp.AppModules.Administration.AdminStaffManagement.RegulationInStaffManagment.RegulationInStaffManagement;
import com.example.tmaadminapp.AppModules.Administration.AdminStaffManagement.WorkerHeadList.WorkerHeadList;
import com.example.tmaadminapp.AppModules.Administration.AdminStaffManagement.WorkerListInAdminPage.WorkersListActivityInAdmin;
import com.example.tmaadminapp.Models.AdminStaffManagementPresenterImplementer;
import com.example.tmaadminapp.Presenters.AdminStaffManagementPresenter;
import com.example.tmaadminapp.R;
import com.example.tmaadminapp.AppModules.UnionCouncilHead.UnionCouncil;
import com.example.tmaadminapp.Views.AdminStaffManagementView;

public class StaffManagementHome extends AppCompatActivity implements AdminStaffManagementView
{

    private AdminStaffManagementPresenter adminStaffManagementPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_management_home);

        adminStaffManagementPresenter = new AdminStaffManagementPresenterImplementer(this);


    }

    // card views click listeners
    public void clickOnWorkerHeadCard(View view)
    {
        adminStaffManagementPresenter.workerHead();
    }

    public void clickOnAppointCard(View view)
    {
        adminStaffManagementPresenter.appointAndRetire();
    }

    public void clickOnWorkerListCard(View view)
    {
        adminStaffManagementPresenter.workerList();
    }

    public void clickOnRegulationCard(View view)
    {
        adminStaffManagementPresenter.regulation();
    }


    // admin staff management call backs methods
    @Override
    public void onWorkerHead() {
        startActivity(new Intent(this , WorkerHeadList.class));
    }

    @Override
    public void onWorkerList() {
        startActivity(new Intent(this , WorkersListActivityInAdmin.class));
    }

    @Override
    public void onAppoint() {
        startActivity(new Intent(this , AppointAndRetireDetails.class));
    }

    @Override
    public void onRegulation() {
        startActivity(new Intent(this , RegulationInStaffManagement.class));
    }
}
