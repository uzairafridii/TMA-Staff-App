package com.example.tmaadminapp.AppModules.Administration.AdminStaffManagement.WorkerHeadList;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tmaadminapp.Models.WorkersHeadPresenterImplementer;
import com.example.tmaadminapp.Presenters.WorkersHeadPresenter;
import com.example.tmaadminapp.R;
import com.example.tmaadminapp.Views.WorkerHeadView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class WorkerHeadList extends AppCompatActivity implements WorkerHeadView
{

    private Toolbar mToolbar;

    private RecyclerView mRecyclerView;
    private AdapterForWorkerHeadRecycler adapter;
    private ProgressDialog progressDialog;
    private WorkersHeadPresenter workersHeadPresenter;
    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_head_list);

        initViews();

        workersHeadPresenter.getAllWorkersHead(databaseReference);


    }

    private void initViews()
    {

        workersHeadPresenter = new WorkersHeadPresenterImplementer(this , this);

        progressDialog = new ProgressDialog(this, R.style.MyAlertDialogStyle);

        // toolbar
        mToolbar = findViewById(R.id.worker_head_tool_bar);
        setTitle("Worker Head");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // recycler view
        mRecyclerView = findViewById(R.id.workerHeadRecycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        /// firebase
        databaseReference = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
    }

    // click on floating action button to add worker head
    public void clickOnAddWorkerFab(View view)
    {
      //  workersHeadPresenter.showSignUpDialog();
        workersHeadPresenter.signUpWorkerHead(databaseReference , mAuth);
    }

    // call backs method of workers head view
    @Override
    public void onShowProgressBar() {
        progressDialog.setMessage("Registering");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
    }

    @Override
    public void onHideProgressBar() {
        progressDialog.dismiss();
    }


    @Override
    public void showMessage(String message) {

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


    // getting all workers head and show in recycler view
    @Override
    public void onGetAllWorkerHeadDetails(List<ModelForWorkerHead> workerHeadList) {

        adapter = new AdapterForWorkerHeadRecycler(workerHeadList , this, databaseReference , this);
        mRecyclerView.setAdapter(adapter);

    }





}
