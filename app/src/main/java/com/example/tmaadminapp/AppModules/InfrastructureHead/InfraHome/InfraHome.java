package com.example.tmaadminapp.AppModules.InfrastructureHead.InfraHome;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.example.tmaadminapp.AppModules.InfrastructureHead.BuildingNoc.BuildingNocActivity;
import com.example.tmaadminapp.AppModules.InfrastructureHead.InfraComplaints.InfraComplaints;
import com.example.tmaadminapp.AppModules.InfrastructureHead.InfraWorkerList.WorkersListActivity;
import com.example.tmaadminapp.AppModules.NewsFeed.NewsFeedActivity.NewsFeedActivity;
import com.example.tmaadminapp.R;

public class InfraHome extends AppCompatActivity {

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infra_home);

        initViews();
    }

    private void initViews()
    {
        mToolbar = findViewById(R.id.infra_tool_bar);
        setSupportActionBar(mToolbar);
        setTitle("Sanitation Home");
    }

    public void clickOnNewsFeedCard(View view)
    {
        startActivity(new Intent(this , NewsFeedActivity.class));
    }


    public void clickOnWorkerListCardView(View view)
    {
        startActivity(new Intent(this , WorkersListActivity.class));
    }

    public void clickOnCompCard(View view)
    {
        startActivity(new Intent(this , InfraComplaints.class));
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
        startActivity(new Intent(this, BuildingNocActivity.class));
    }
}
