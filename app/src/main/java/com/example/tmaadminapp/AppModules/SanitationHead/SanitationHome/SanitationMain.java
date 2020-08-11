package com.example.tmaadminapp.AppModules.SanitationHead.SanitationHome;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.tmaadminapp.AppModules.Complaints.SanitationComplaints;
import com.example.tmaadminapp.AppModules.FeedbackWorks.SanitationFeedbackWorkActivity;
import com.example.tmaadminapp.AppModules.NewsFeed.NewsFeedActivity.NewsFeedActivity;
import com.example.tmaadminapp.Models.SanitationMainPresenterImplementer;
import com.example.tmaadminapp.Presenters.SanitationMainPresenter;
import com.example.tmaadminapp.R;
import com.example.tmaadminapp.AppModules.WorkersListAndDetails.Activities.SanitationWorkersListActivity;
import com.example.tmaadminapp.Views.SanitationMainView;

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

    public void clickOnComplaintsFeedbackCardView(View view)
    {
         sanitationMainPresenter.feedback();
    }



    // sanitation view callbacks
    @Override
    public void onNewsFeedCardClick() {
        startActivity(new Intent(this , NewsFeedActivity.class));
    }

    @Override
    public void onWorkerListCardClick() {
        startActivity(new Intent(SanitationMain.this , SanitationWorkersListActivity.class));
    }

    @Override
    public void onComplaintCardClick() {
        startActivity(new Intent(SanitationMain.this , SanitationComplaints.class));
    }

    @Override
    public void onFeedBackCardClick()
    {
      startActivity(new Intent(SanitationMain.this , SanitationFeedbackWorkActivity.class));
    }


}
