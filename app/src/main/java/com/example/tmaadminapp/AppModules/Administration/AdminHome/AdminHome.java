package com.example.tmaadminapp.AppModules.Administration.AdminHome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.tmaadminapp.AppModules.Administration.AdminStaffManagement.StaffManagementHome.StaffManagementHome;
import com.example.tmaadminapp.AppModules.Administration.NewsFeedForAdmin.NewsFeedForAdmin;
import com.example.tmaadminapp.AppModules.InfrastructureHead.InfraHome.InfraHome;
import com.example.tmaadminapp.R;
import com.example.tmaadminapp.AppModules.SanitationHead.SanitationHome.SanitationMain;

public class AdminHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
    }

    public void complaintCardClick(View view)
    {
        startActivity(new Intent(this , SanitationMain.class));
    }


    public void financeCardClick(View view)
    {
        startActivity(new Intent(this , InfraHome.class));
    }

    public void staffManagementCardClick(View view)
    {
        startActivity(new Intent(this , StaffManagementHome.class));

    }


    public void newsFeedCardClick(View view)
    {
        startActivity(new Intent(this, NewsFeedForAdmin.class));
    }
}
