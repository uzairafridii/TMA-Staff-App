package com.example.tmaadminapp.AppModules.SanitationHead.SanitationHome;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.tmaadminapp.AppModules.NewsFeed.NewsFeedActivity.NewsFeedActivity;
import com.example.tmaadminapp.R;
import com.example.tmaadminapp.AppModules.SanitationHead.SanitationComplaints.Complaints;
import com.example.tmaadminapp.AppModules.SanitationHead.SanitationWorkers.SanitationWorkerList;

public class SanitationMain extends AppCompatActivity {

    private Toolbar mToolbar;

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
    }

    public void clickOnNewsFeedCard(View view)
    {
        startActivity(new Intent(this , NewsFeedActivity.class));
    }

    public void clickOnWorkerListCard(View view)
    {
        startActivity(new Intent(SanitationMain.this , SanitationWorkerList.class));
    }

    public void clickOnComplaintsCardView(View view)
    {
        startActivity(new Intent(SanitationMain.this , Complaints.class));
    }
}
