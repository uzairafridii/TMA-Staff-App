package com.example.tmaadminapp.AppModules.InfrastructureHead.InfraComplaints;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.tmaadminapp.R;
import com.google.android.material.tabs.TabLayout;

public class InfraComplaints extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infra_complaints);
       initViews();

    }

    private void initViews()
    {
        tabLayout = findViewById(R.id.infraTabsLayout);

        mToolbar = findViewById(R.id.infraComplaintsToolbar);
        setSupportActionBar(mToolbar);
        setTitle("Complaints");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = findViewById(R.id.infraViewPager);

        InfraViewPagerAdapter adapter = new InfraViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);
    }
}
