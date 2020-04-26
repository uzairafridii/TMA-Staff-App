package com.example.tmaadminapp.AppModules.SanitationHead.SanitationHome;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.tmaadminapp.AppModules.NewsFeed.NewsFeedActivity.NewsFeedActivity;
import com.example.tmaadminapp.Models.SanitationMainPresenterImplementer;
import com.example.tmaadminapp.Presenters.SanitationMainPresenter;
import com.example.tmaadminapp.R;
import com.example.tmaadminapp.AppModules.SanitationHead.SanitationComplaints.Complaints;
import com.example.tmaadminapp.AppModules.SanitationHead.SanitationWorkers.SanitationWorkerList;
import com.example.tmaadminapp.Views.SanitationMainView;
import com.google.firebase.auth.FirebaseAuth;

public class SanitationMain extends AppCompatActivity implements SanitationMainView
{

    private Toolbar mToolbar;
    private SanitationMainPresenter sanitationMainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sanitation_main);
        initViews();

    }

    private void initViews()
    {
        mToolbar = findViewById(R.id.sanitation_tool_bar);
        setSupportActionBar(mToolbar);
        setTitle("Sanitation Home");

        sanitationMainPresenter = new SanitationMainPresenterImplementer(this);
    }

    // card views click listeners
    public void clickOnNewsFeedCard(View view)
    {
        sanitationMainPresenter.newsFeed();
    }

    public void clickOnWorkerListCard(View view)
    {
        sanitationMainPresenter.workersList();

    }

    public void clickOnComplaintsCardView(View view)
    {
        sanitationMainPresenter.complaints();

    }



    // sanitation view callbacks
    @Override
    public void onNewsFeedCardClick() {
        startActivity(new Intent(this , NewsFeedActivity.class));
    }

    @Override
    public void onWorkerListCardClick() {
        startActivity(new Intent(SanitationMain.this , SanitationWorkerList.class));
    }

    @Override
    public void onComplaintCardClick() {
        startActivity(new Intent(SanitationMain.this , Complaints.class));
    }

}
