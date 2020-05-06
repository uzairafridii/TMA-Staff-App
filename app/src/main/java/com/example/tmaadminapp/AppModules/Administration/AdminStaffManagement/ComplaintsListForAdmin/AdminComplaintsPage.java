package com.example.tmaadminapp.AppModules.Administration.AdminStaffManagement.ComplaintsListForAdmin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tmaadminapp.Models.AdminComplaintPresenterImplementer;
import com.example.tmaadminapp.Presenters.AdminComplaintPreseneter;
import com.example.tmaadminapp.R;
import com.example.tmaadminapp.Views.AdminCompaintView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class AdminComplaintsPage extends AppCompatActivity implements AdminCompaintView {

    private Toolbar mToolbar;
    private RecyclerView totalComplaintRecyclerView;
    private TextView noItemFoundTextView;
    private LinearLayoutManager layoutManager;
    private AdapterTotalComplaintRv adapterTotalComplaintRv;
    private AdminComplaintPreseneter adminComplaintPreseneter;
    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_complaints_page);

        initViews();
        adminComplaintPreseneter.getAllComplaints(databaseReference);
    }

    private void initViews()
    {

        adminComplaintPreseneter = new AdminComplaintPresenterImplementer(this);

        // tool bar
        mToolbar = findViewById(R.id.adminTotalComplaintToolbar);
        setSupportActionBar(mToolbar);
        setTitle("Total SanitationComplaints");

        noItemFoundTextView = findViewById(R.id.noItemFoundTextView);

        // recycler view
        totalComplaintRecyclerView = findViewById(R.id.allComplaintsRv);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setStackFromEnd(true);
        layoutManager.setReverseLayout(true);
        totalComplaintRecyclerView.setLayoutManager(layoutManager);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("SanitationComplaints");


    }

    /*
     * get list of items and display in recycler view
     */
    @Override
    public void onGetComplaints(List<ModelForTotalComplaints> complaintsList) {
        adapterTotalComplaintRv = new AdapterTotalComplaintRv(this, complaintsList , this);
        totalComplaintRecyclerView.setAdapter(adapterTotalComplaintRv);
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onHideTextView() {
        noItemFoundTextView.setVisibility(View.INVISIBLE);
    }

}
