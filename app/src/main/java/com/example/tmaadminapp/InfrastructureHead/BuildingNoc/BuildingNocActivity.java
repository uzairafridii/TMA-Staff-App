package com.example.tmaadminapp.InfrastructureHead.BuildingNoc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import com.example.tmaadminapp.R;

public class BuildingNocActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building_noc);

        initViews();
    }

    private void initViews()
    {
        mToolbar = findViewById(R.id.noc_tool_bar);
        setTitle("Building Noc");
        setSupportActionBar(mToolbar);
    }
}
