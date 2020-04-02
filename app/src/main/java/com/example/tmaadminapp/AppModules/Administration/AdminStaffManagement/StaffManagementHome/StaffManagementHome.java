package com.example.tmaadminapp.AppModules.Administration.AdminStaffManagement.StaffManagementHome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.tmaadminapp.AppModules.Administration.AdminStaffManagement.AppointAndRetireInStaffManagement.AppointAndRetireDetails;
import com.example.tmaadminapp.AppModules.Administration.AdminStaffManagement.WorkerHeadList.WorkerHeadList;
import com.example.tmaadminapp.AppModules.Administration.AdminStaffManagement.WorkerListInAdminPage.WorkersListActivityInAdmin;
import com.example.tmaadminapp.R;
import com.example.tmaadminapp.AppModules.UnionCouncilHead.UnionCouncil;

public class StaffManagementHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_management_home);
    }

    public void clickOnWorkerHeadCard(View view)
    {
        startActivity(new Intent(this , WorkerHeadList.class));
    }

    public void clickOnAppointCard(View view)
    {
        startActivity(new Intent(this , AppointAndRetireDetails.class));
    }

    public void clickOnWorkerListCard(View view)
    {
        startActivity(new Intent(this , WorkersListActivityInAdmin.class));
    }

    public void clickOnRegulationCard(View view)
    {
       startActivity(new Intent(this , UnionCouncil.class));
    }
}
