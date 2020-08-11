package com.example.tmaadminapp.AppModules.Administration.AdminStaffManagement.WorkerListInAdminPage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.tmaadminapp.Models.AdminWorkersListPresenterImplementer;
import com.example.tmaadminapp.Presenters.AdminWorkerListPresenter;
import com.example.tmaadminapp.R;
import com.example.tmaadminapp.Views.AdminWorkersListView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class WorkersListActivityInAdmin extends AppCompatActivity implements AdminWorkersListView
{

    private RecyclerView mRecyclerView;
    private Toolbar mToolbar;
    private LinearLayout noItemFound;
    private AdapterForWorkerListRecyclerInAdmin adapter;
    private AdminWorkerListPresenter workerListPresenter;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_head_workers_list);

        initViews();
        workerListPresenter.onGetWorkersList(databaseReference);


    }

    private void initViews()
    {
        workerListPresenter = new AdminWorkersListPresenterImplementer(this);

        mRecyclerView = findViewById(R.id.worker_list_recycler_in_admin);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mToolbar = findViewById(R.id.tool_bar_in_admin_page);
        setSupportActionBar(mToolbar);
        setTitle("Worker List");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        noItemFound = findViewById(R.id.noItemFoundLayout);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Worker List");

    }

    @Override
    public void getWorkerList(List<ModelForWorkerLIstInAdmin> list)
    {
      adapter = new AdapterForWorkerListRecyclerInAdmin(list, this, this);
      mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void hideNoItemFoundLayout() {
        noItemFound.setVisibility(View.GONE);
    }

    @Override
    public void showNoItemFoundLayout() {
         noItemFound.setVisibility(View.VISIBLE);
    }
}
