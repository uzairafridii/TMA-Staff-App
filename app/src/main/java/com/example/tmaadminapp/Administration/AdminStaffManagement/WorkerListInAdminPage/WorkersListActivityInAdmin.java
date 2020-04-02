package com.example.tmaadminapp.Administration.AdminStaffManagement.WorkerListInAdminPage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.tmaadminapp.R;
import com.example.tmaadminapp.SanitationHead.SanitationWorkers.AdapterForWorkerList;
import com.example.tmaadminapp.SanitationHead.SanitationWorkers.ModelForWorkerList;

import java.util.ArrayList;

public class WorkersListActivityInAdmin extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private Toolbar mToolbar;
    private ArrayList<ModelForWorkerLIstInAdmin> listOfWorker;
    private AdapterForWorkerListRecyclerInAdmin adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_head_workers_list);

        initViews();
        addItems();
        mRecyclerView.setAdapter(adapter);

    }

    private void initViews()
    {
        mRecyclerView = findViewById(R.id.worker_list_recycler_in_admin);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        //tool bar
        mToolbar = findViewById(R.id.tool_bar_in_admin_page);
        setSupportActionBar(mToolbar);
        setTitle("Worker List");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // array list
        listOfWorker = new ArrayList<>();
        // adapter
        adapter = new AdapterForWorkerListRecyclerInAdmin(listOfWorker , this);
    }

    // add items to recycler view
    private void addItems()
    {
        listOfWorker.add(new ModelForWorkerLIstInAdmin("First", "Sanitation", 3.2f));
        listOfWorker.add(new ModelForWorkerLIstInAdmin("Second", "Infrastructure", 2.2f));
        listOfWorker.add(new ModelForWorkerLIstInAdmin("Third", "Sanitation", 4.2f));
        listOfWorker.add(new ModelForWorkerLIstInAdmin("Fourth", "Sanitation", 5f));
        listOfWorker.add(new ModelForWorkerLIstInAdmin("Fifth", "Infrastructure", 4.5f));

    }
}
