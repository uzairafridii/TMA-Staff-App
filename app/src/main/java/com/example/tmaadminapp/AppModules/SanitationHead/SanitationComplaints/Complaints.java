package com.example.tmaadminapp.AppModules.SanitationHead.SanitationComplaints;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.tmaadminapp.R;
import com.google.android.material.tabs.TabLayout;

public class Complaints extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaints);

        initViews();

    }

    private void initViews()
    {
        tabLayout = findViewById(R.id.tabsLayout);

        mToolbar = findViewById(R.id.complaintsToolbar);
        setSupportActionBar(mToolbar);
        setTitle("Complaints");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = findViewById(R.id.viewPager);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);
    }
}
