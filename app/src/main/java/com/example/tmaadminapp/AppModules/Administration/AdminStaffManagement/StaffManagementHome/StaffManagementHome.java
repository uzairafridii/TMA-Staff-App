package com.example.tmaadminapp.AppModules.Administration.AdminStaffManagement.StaffManagementHome;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.tmaadminapp.AppModules.Administration.AdminStaffManagement.WorkerHeadList.WorkerHeadList;
import com.example.tmaadminapp.AppModules.Administration.AdminStaffManagement.WorkerListInAdminPage.WorkersListActivityInAdmin;
import com.example.tmaadminapp.Models.AdminStaffManagementPresenterImplementer;
import com.example.tmaadminapp.Presenters.AdminStaffManagementPresenter;
import com.example.tmaadminapp.R;
import com.example.tmaadminapp.AppModules.UnionCouncilHead.UnionCouncil;
import com.example.tmaadminapp.Views.AdminStaffManagementView;

public class StaffManagementHome extends AppCompatActivity implements AdminStaffManagementView {

    private AdminStaffManagementPresenter adminStaffManagementPresenter;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_management_home);

        initViews();
    }

    private void initViews()
    {
        mToolbar = findViewById(R.id.staff_home_tool_bar);
        setSupportActionBar(mToolbar);
        setTitle("Staff Management");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        adminStaffManagementPresenter = new AdminStaffManagementPresenterImplementer(this);
    }

    // card views click listeners
    public void clickOnWorkerHeadCard(View view) {
        adminStaffManagementPresenter.workerHead();
    }

    public void clickOnAppointCard(View view) {
        adminStaffManagementPresenter.appointAndRetire();
    }

    public void clickOnWorkerListCard(View view) {
        adminStaffManagementPresenter.workerList();
    }

    public void clickOnRegulationCard(View view) {
        adminStaffManagementPresenter.regulation();
    }


    // admin staff management call backs methods
    @Override
    public void onWorkerHead() {
        startActivity(new Intent(this, WorkerHeadList.class));
    }

    @Override
    public void onWorkerList() {
        startActivity(new Intent(this, WorkersListActivityInAdmin.class));
    }

    @Override
    public void onAppoint() {
        Toast.makeText(this, "Not yet work", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRegulation() {
        Toast.makeText(this, "Not yet work", Toast.LENGTH_SHORT).show();
    }
}
