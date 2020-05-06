package com.example.tmaadminapp.AppModules.Complaints;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.tmaadminapp.Models.ComplaintsPresenterImplementer;
import com.example.tmaadminapp.Presenters.ComplaintsPresenter;
import com.example.tmaadminapp.R;
import com.example.tmaadminapp.Views.ComplaintsView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class InfraComplaints extends AppCompatActivity implements ComplaintsView
{

    private Toolbar mToolbar;
    private TextView noItemsText;
    private RecyclerView listOfInfraComplaints;
    private AdapterForComplaintRv adapter;
    private LinearLayoutManager layoutManager;
    private ComplaintsPresenter complaintsPresenter;
    private DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infra_complaints2);

        initViews();

        complaintsPresenter.getTotalComplaints(dbRef , "Infrastructure");

    }

    private void initViews()
    {
        complaintsPresenter = new ComplaintsPresenterImplementer(this);

        mToolbar = findViewById(R.id.infraComplaintsToolbar);
        setSupportActionBar(mToolbar);
        setTitle("Infra Complaints");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listOfInfraComplaints = findViewById(R.id.infraComplaintRecycler);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        listOfInfraComplaints.setLayoutManager(layoutManager);

        noItemsText = findViewById(R.id.infraNoItemText);

        dbRef = FirebaseDatabase.getInstance().getReference().child("Complaints");


    }

    @Override
    public void onHideTextNoItem() {
        noItemsText.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onGetAllSanitationComplaints(List<ModelForComplaints> complaintsList)
    {
        adapter = new AdapterForComplaintRv(complaintsList , this , this);
        listOfInfraComplaints.setAdapter(adapter);
    }
}
