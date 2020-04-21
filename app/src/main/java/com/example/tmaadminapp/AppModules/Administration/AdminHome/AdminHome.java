package com.example.tmaadminapp.AppModules.Administration.AdminHome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.tmaadminapp.AppModules.Administration.AdminStaffManagement.ComplaintsListForAdmin.AdminComplaintsPage;
import com.example.tmaadminapp.AppModules.Administration.AdminStaffManagement.StaffManagementHome.StaffManagementHome;
import com.example.tmaadminapp.AppModules.Administration.NewsFeedForAdmin.NewsFeedForAdmin;
import com.example.tmaadminapp.AppModules.InfrastructureHead.InfraHome.InfraHome;
import com.example.tmaadminapp.Models.AdminHomePresenterImplementer;
import com.example.tmaadminapp.Presenters.AdminHomePresenter;
import com.example.tmaadminapp.R;
import com.example.tmaadminapp.AppModules.SanitationHead.SanitationHome.SanitationMain;
import com.example.tmaadminapp.Views.AdminHomeView;

public class AdminHome extends AppCompatActivity implements AdminHomeView
{

    private AdminHomePresenter adminHomePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        adminHomePresenter = new AdminHomePresenterImplementer(this);
    }

    // call backs methods of admin home view
    @Override
    public void onNewsFeedCardClick()
    {
        startActivity(new Intent(AdminHome.this , NewsFeedForAdmin.class));
    }

    @Override
    public void onStaffManagementCardClick() {
        startActivity(new Intent(AdminHome.this , StaffManagementHome.class));
    }

    @Override
    public void onFinanceCardClick() {
        Toast.makeText(this, "Not Yet Work", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onComplaintsCardClick() {

        startActivity(new Intent(AdminHome.this , AdminComplaintsPage.class));

    }



    /*
     * click listeners on cards view
     */
    public void newsFeedCardClick(View view) {
        adminHomePresenter.newsFeedCardClick();
    }

    public void staffManagementCardClick(View view) {
        adminHomePresenter.staffManagementCardClick();
    }

    public void financeCardClick(View view)
    {
        adminHomePresenter.financeCardClick();
    }

    public void complaintCardClick(View view)
    {
        adminHomePresenter.complaintsCardClick();
    }

}
