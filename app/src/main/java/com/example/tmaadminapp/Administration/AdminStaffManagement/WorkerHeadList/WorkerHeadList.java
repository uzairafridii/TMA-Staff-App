package com.example.tmaadminapp.Administration.AdminStaffManagement.WorkerHeadList;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.example.tmaadminapp.R;

import java.util.ArrayList;

public class WorkerHeadList extends AppCompatActivity {

    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private AdapterForWorkerHeadRecycler adapter;
    private ArrayList<ModelForWorkerHead> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_head_list);

        initViews();
        addItems();

        mRecyclerView.setAdapter(adapter);

    }

    private void initViews()
    {
        // toolbar
        mToolbar = findViewById(R.id.worker_head_tool_bar);
        setTitle("Worker Head");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // recycler view
        mRecyclerView = findViewById(R.id.workerHeadRecycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // array list
        arrayList = new ArrayList<>();

        // adapter
        adapter = new AdapterForWorkerHeadRecycler(arrayList , this);
    }


    // add dummy data to recycler view
    private void addItems()
    {
        arrayList.add(new ModelForWorkerHead("First", "Sanitation"));
        arrayList.add(new ModelForWorkerHead("Second", "Infrastructure"));
        arrayList.add(new ModelForWorkerHead("Third", "Regulation"));
        arrayList.add(new ModelForWorkerHead("Fourth", "Union Council"));
    }

    // click on floating action button to add worker head
    public void clickOnAddWorkerFab(View view)
    {
        View customView = LayoutInflater.from(this).inflate(R.layout.add_worker_dialog_layout, null);
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setView(customView);
        alert.setCancelable(false);

        AlertDialog dialog = alert.create();




        dialog.show();
    }

}
