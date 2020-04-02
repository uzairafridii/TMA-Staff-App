package com.example.tmaadminapp.UnionCouncilHead;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.tmaadminapp.R;
import com.example.tmaadminapp.SanitationHead.SanitationComplaints.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class UcMain extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uc_main);

        initViews();

    }

    private void initViews()
    {
        tabLayout = findViewById(R.id.tabsLayoutInUc);

        mToolbar = findViewById(R.id.ucToolbar);
        setSupportActionBar(mToolbar);
        setTitle("Union Council");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = findViewById(R.id.viewPagerInUc);

        ViewPagerUcAdapter adapter = new ViewPagerUcAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);
    }
}
