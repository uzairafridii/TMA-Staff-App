package com.example.tmaadminapp.AppModules.WorkersListAndDetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.tmaadminapp.Models.AddWorkerPresenterImplementer;
import com.example.tmaadminapp.Presenters.AddWorkerPresenter;
import com.example.tmaadminapp.R;
import com.example.tmaadminapp.Views.AddWorkerView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class InfraWorkersListActivity extends AppCompatActivity implements AddWorkerView
{
    private Toolbar mToolbar;
    private RecyclerView infraWorkersList;
    private AdapterForWorkerList adapter;
    private LinearLayoutManager layoutManager;
    private AddWorkerPresenter presenter;
    private ProgressDialog progressDialog;
    private DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infra_workers_list);

        initViews();

        presenter.getAllWorkers(dbRef , "Infrastructure");

    }

    private void initViews()
    {
        presenter = new AddWorkerPresenterImplementer(this , this);

        //recycler view
        infraWorkersList = findViewById(R.id.inFraWorkerList);
        layoutManager = new LinearLayoutManager(this);
        infraWorkersList.setLayoutManager(layoutManager);

        // toolbar
        mToolbar = findViewById(R.id.infra_worker_list_tool_bar);
        setSupportActionBar(mToolbar);
        setTitle("Worker List");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        progressDialog = new ProgressDialog(this , R.style.MyAlertDialogStyle);

        dbRef = FirebaseDatabase.getInstance().getReference().child("Worker List");

    }

    // fab button click to add worker
    public void fabAddWorkerClick(View view)
    {
        presenter.fabClick(dbRef , "Infrastructure");
    }


      // callbacks method of worker list view
    @Override
    public void showProgressBar()
    {
        progressDialog.setMessage("Registering worker");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
    }

    @Override
    public void hideProgressBar()
    {
        progressDialog.dismiss();
    }

    @Override
    public void showMessage(String message)
    {

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getWorkersList(List<ModelForWorkerList> workerList)
    {
        adapter = new AdapterForWorkerList(workerList , this);
        infraWorkersList.setAdapter(adapter);
    }

}
