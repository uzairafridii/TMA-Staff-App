package com.example.tmaadminapp.AppModules.Regulation.Activities.Bills;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.tmaadminapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class WaterBillsAndLocation extends AppCompatActivity {

    private Toolbar mToolbar;
    private LinearLayout noItemFound;
    private RecyclerView waterBillsList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_bills_and_location);
        setTitle("Water Bills");

        initViews();

    }

    private void initViews()
    {
           waterBillsList = findViewById(R.id.rvBills);
           waterBillsList.setLayoutManager(new LinearLayoutManager(this));
           noItemFound = findViewById(R.id.noBillFound);

           mToolbar = findViewById(R.id.billToolbar);
           setSupportActionBar(mToolbar);
           getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }


    //fab button click listener
    public void addNewConnection(View view)
    {

    }
}
