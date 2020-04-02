package com.example.tmaadminapp.InfrastructureHead.InfraWorkerList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.example.tmaadminapp.R;
import com.example.tmaadminapp.SanitationHead.SanitationWorkers.AdapterForWorkerList;
import com.example.tmaadminapp.SanitationHead.SanitationWorkers.ModelForWorkerList;

import java.util.ArrayList;

public class WorkersListActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private RecyclerView workerListRecycler;
    private ArrayList<ModelForWorkerList> listOfWorker;
    private AdapterForWorkerList adapter;
    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workers_list);

        initViews();
        addItems();

        workerListRecycler.setAdapter(adapter);



    }

    private void initViews()
    {
        //recycler view
        workerListRecycler = findViewById(R.id.infraWorkerList);
        layoutManager = new LinearLayoutManager(this);
        workerListRecycler.setLayoutManager(layoutManager);


        // array list
        listOfWorker = new ArrayList<>();

        // adapter
        adapter = new AdapterForWorkerList(listOfWorker , this);

        // toolbar
        mToolbar = findViewById(R.id.infra_worker_list_tool_bar);
        setSupportActionBar(mToolbar);
        setTitle("Worker List");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    public void fabAddWorkerClick(View view)
    {
        View customView =  LayoutInflater.from(this).inflate(R.layout.add_worker_dialog_layout, null);
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setView(customView);

        AlertDialog dialog = alert.create();
        dialog.setCanceledOnTouchOutside(false);

        // TextView workerName , workerPhoneNo, workerCnic;
        //  Button addWorkerButton;

        dialog.show();


    }


    // add items to recycler view
    private void addItems()
    {
        listOfWorker.add(new ModelForWorkerList("Asad" , 3.2f));
        listOfWorker.add(new ModelForWorkerList("Khan" , 2.7f));
        listOfWorker.add(new ModelForWorkerList("Asif" , 2.3f));
        listOfWorker.add(new ModelForWorkerList("Uzair" , 5f));
        listOfWorker.add(new ModelForWorkerList("Dr" , 3.8f));
        listOfWorker.add(new ModelForWorkerList("Afridi" , 4f));
    }
}
