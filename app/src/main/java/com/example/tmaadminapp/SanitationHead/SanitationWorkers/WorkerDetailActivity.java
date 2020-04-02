package com.example.tmaadminapp.SanitationHead.SanitationWorkers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import com.example.tmaadminapp.R;

public class WorkerDetailActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_detail);
        initViews();
    }

    private void initViews()
    {

        mToolbar = findViewById(R.id.workerDetailToolBar);
        setTitle("Worker Details");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
