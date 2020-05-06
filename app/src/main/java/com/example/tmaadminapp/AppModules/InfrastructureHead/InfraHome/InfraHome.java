package com.example.tmaadminapp.AppModules.InfrastructureHead.InfraHome;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.example.tmaadminapp.AppModules.Complaints.InfraComplaints;
import com.example.tmaadminapp.AppModules.FeedbackWorks.InfraFeedbackWorksActivity;
import com.example.tmaadminapp.AppModules.InfrastructureHead.BuildingNoc.BuildingNocActivity;
import com.example.tmaadminapp.AppModules.NewsFeed.NewsFeedActivity.NewsFeedActivity;
import com.example.tmaadminapp.AppModules.WorkersListAndDetails.InfraWorkersListActivity;
import com.example.tmaadminapp.AppModules.WorkersListAndDetails.SanitationWorkersListActivity;
import com.example.tmaadminapp.Models.InfraHomePresenterImplementer;
import com.example.tmaadminapp.Presenters.InfraHomePresenter;
import com.example.tmaadminapp.R;
import com.example.tmaadminapp.Views.InfraHomeView;

public class InfraHome extends AppCompatActivity  implements InfraHomeView
{

    private Toolbar mToolbar;
    private InfraHomePresenter infraHomePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infra_home);

        initViews();
    }

    private void initViews()
    {
         infraHomePresenter = new InfraHomePresenterImplementer(this);

        mToolbar = findViewById(R.id.infra_tool_bar);
        setSupportActionBar(mToolbar);
        setTitle("Infra Home");
    }

    // cards views click listeners
    public void clickOnFeedbackCard(View view)
    {
        infraHomePresenter.clickOnComplaintFeedbacksCard();
    }

    public void clickOnNewsFeedCard(View view)
    {
        infraHomePresenter.clickOnNewsFeedCard();
    }

    public void clickOnWorkerListCardView(View view)
    {
        infraHomePresenter.clickOnWorkersListCard();
    }

    public void clickOnCompCard(View view)
    {
        infraHomePresenter.clickOnComplaintsCard();
    }

    public void clickOnFireFightingCard(View view)
    {
        // inflate edit worker layout
        View myView = LayoutInflater.from(this).inflate(R.layout.add_fire_fighting_details, null);
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setView(myView);

        final AlertDialog dialog = alertBuilder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);

        Button btn = myView.findViewById(R.id.add_driver_btn_fire_layout);
        // click on update button in update layout
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.cancel();
            }
        });
        dialog.show();
    }

    public void clickOnBuildingNoc(View view)
    {
        infraHomePresenter.clickOnBuildingNocCard();
    }



    // infra home view callbacks methods
    @Override
    public void onNewsFeedClick() {
        startActivity(new Intent(this , NewsFeedActivity.class));
    }

    @Override
    public void onComplaintsClick() {
        startActivity(new Intent(this , InfraComplaints.class));
    }

    @Override
    public void onWorkerListClick() {
        startActivity(new Intent(this , InfraWorkersListActivity.class));
    }

    @Override
    public void onBuildingNocClick() {
        startActivity(new Intent(this, BuildingNocActivity.class));

    }

    @Override
    public void onComplaintFeedbackClick() {
        startActivity(new Intent(this, InfraFeedbackWorksActivity.class));

    }

    @Override
    public void onFireFightingClick() {

    }


}
