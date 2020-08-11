package com.example.tmaadminapp.AppModules.Complaints;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.tmaadminapp.Models.ComplaintsPresenterImplementer;
import com.example.tmaadminapp.Presenters.ComplaintsPresenter;
import com.example.tmaadminapp.R;
import com.example.tmaadminapp.Views.ComplaintsView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class SanitationComplaints extends AppCompatActivity implements ComplaintsView
{
    private Toolbar mToolbar;
    private LinearLayout noItemsLayout;
    private RecyclerView listOfSanitationComplaints;
    private AdapterForComplaintRv adapter;
    private LinearLayoutManager layoutManager;
    private ComplaintsPresenter complaintsPresenter;
    private DatabaseReference dbRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaints);

        initViews();

        complaintsPresenter.getTotalComplaints(dbRef , "Sanitation");

    }

    private void initViews()
    {
        complaintsPresenter = new ComplaintsPresenterImplementer(this);

        mToolbar = findViewById(R.id.complaintsToolbar);
        setSupportActionBar(mToolbar);
        setTitle("SanitationComplaints");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listOfSanitationComplaints = findViewById(R.id.sanitationComplaintRecycler);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        listOfSanitationComplaints.setLayoutManager(layoutManager);

        noItemsLayout = findViewById(R.id.noItemFoundLayout);

        dbRef = FirebaseDatabase.getInstance().getReference().child("Complaints");


    }

    @Override
    public void onHideTextNoItem() {
        noItemsLayout.setVisibility(View.GONE);
    }

    @Override
    public void onGetAllSanitationComplaints(List<ModelForComplaints> complaintsList)
    {
        adapter = new AdapterForComplaintRv(complaintsList , this , this);
        listOfSanitationComplaints.setAdapter(adapter);
    }

}
