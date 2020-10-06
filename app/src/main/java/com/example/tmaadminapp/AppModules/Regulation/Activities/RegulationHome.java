package com.example.tmaadminapp.AppModules.Regulation.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.tmaadminapp.AppModules.NewsFeed.NewsFeedActivity.NewsFeedActivity;
import com.example.tmaadminapp.AppModules.Regulation.Activities.Bills.WaterBillsAndLocation;
import com.example.tmaadminapp.AppModules.Regulation.Activities.Tax.TaxesDetailsActivity;
import com.example.tmaadminapp.R;

public class RegulationHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regulation_home);
    }

    // click on news feed card
    public void newFeedCardClick(View view)
    {
      startActivity(new Intent(this, NewsFeedActivity.class));
    }

    public void onWaterBillCardClick(View view)
    {
        startActivity(new Intent(this, WaterBillsAndLocation.class));
    }

    public void onTaxesDetailsCardClick(View view)
    {
        startActivity(new Intent(this , TaxesDetailsActivity.class));
    }
}
