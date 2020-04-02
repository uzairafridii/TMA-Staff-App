package com.example.tmaadminapp.SanitationHead.ComplaintFragements.CompletedComplaints;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import com.example.tmaadminapp.R;

public class AddCompletedWorkOnComplaint extends AppCompatActivity {

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_completed_work_on_complaint);
        initViews();

    }

    private void initViews()
    {
        mToolbar = findViewById(R.id.completed_complaint_tool_bar);
        setTitle("Rate Us");
        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }
}
