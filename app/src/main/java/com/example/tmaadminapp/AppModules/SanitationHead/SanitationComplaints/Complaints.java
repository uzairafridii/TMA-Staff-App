package com.example.tmaadminapp.AppModules.SanitationHead.SanitationComplaints;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tmaadminapp.AppModules.Administration.AdminStaffManagement.ComplaintsListForAdmin.ModelForTotalComplaints;
import com.example.tmaadminapp.Models.ComplaintsPresenterImplementer;
import com.example.tmaadminapp.Presenters.ComplaintsPresenter;
import com.example.tmaadminapp.R;
import com.example.tmaadminapp.Views.ComplaintsView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class Complaints extends AppCompatActivity implements ComplaintsView
{
    private Toolbar mToolbar;
    private TextView noItemsText;
    private RecyclerView listOfSanitationComplaints;
    private AdapterForSanitationComplaintRv adapter;
    private LinearLayoutManager layoutManager;
    private ComplaintsPresenter complaintsPresenter;
    private DatabaseReference dbRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaints);

        initViews();

        complaintsPresenter.getTotalComplaints(dbRef);

    }

    private void initViews()
    {
        complaintsPresenter = new ComplaintsPresenterImplementer(this);

        mToolbar = findViewById(R.id.complaintsToolbar);
        setSupportActionBar(mToolbar);
        setTitle("Complaints");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listOfSanitationComplaints = findViewById(R.id.sanitationComplaintRecycler);
        layoutManager = new LinearLayoutManager(this);
        listOfSanitationComplaints.setHasFixedSize(true);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        listOfSanitationComplaints.setLayoutManager(layoutManager);

        noItemsText = findViewById(R.id.sanitationNoItemText);

        dbRef = FirebaseDatabase.getInstance().getReference().child("Complaints");


    }

    @Override
    public void onHideTextNoItem() {
        noItemsText.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onGetAllSanitationComplaints(List<ModelForComplaints> complaintsList)
    {
        Toast.makeText(this, "Set Adapter Method called", Toast.LENGTH_SHORT).show();
        adapter = new AdapterForSanitationComplaintRv(complaintsList , this , this);
        listOfSanitationComplaints.setAdapter(adapter);
    }

}
