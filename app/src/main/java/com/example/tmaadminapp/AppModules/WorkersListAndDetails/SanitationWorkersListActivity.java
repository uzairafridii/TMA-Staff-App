package com.example.tmaadminapp.AppModules.WorkersListAndDetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tmaadminapp.Models.AddWorkerPresenterImplementer;
import com.example.tmaadminapp.Presenters.AddWorkerPresenter;
import com.example.tmaadminapp.R;
import com.example.tmaadminapp.Views.AddWorkerView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class SanitationWorkersListActivity extends AppCompatActivity implements AddWorkerView
{
    private Toolbar mToolbar;
    private RecyclerView workerListRecycler;
    private AdapterForWorkerList adapter;
    private LinearLayoutManager layoutManager;
    private AddWorkerPresenter presenter;
    private ProgressDialog progressDialog;
    private DatabaseReference dbRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workers_list_activity);

        initViews();

        presenter.getAllWorkers(dbRef , "Sanitation");

    }

    private void initViews()
    {
         presenter = new AddWorkerPresenterImplementer(this , this);

        //recycler view
        workerListRecycler = findViewById(R.id.sanitationWorkerList);
        layoutManager = new LinearLayoutManager(this);
        workerListRecycler.setLayoutManager(layoutManager);

        // toolbar
        mToolbar = findViewById(R.id.sanitation_worker_list_tool_bar);
        setSupportActionBar(mToolbar);
        setTitle("Worker List");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        progressDialog = new ProgressDialog(this , R.style.MyAlertDialogStyle);

        dbRef = FirebaseDatabase.getInstance().getReference().child("Worker List");

    }

    // fab button click to add worker
    public void fabAddWorkerClick(View view)
    {
       presenter.fabClick(dbRef , "Sanitation");
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
          workerListRecycler.setAdapter(adapter);
    }

}



