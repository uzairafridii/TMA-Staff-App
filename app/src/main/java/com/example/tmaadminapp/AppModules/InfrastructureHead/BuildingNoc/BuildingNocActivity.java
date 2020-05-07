package com.example.tmaadminapp.AppModules.InfrastructureHead.BuildingNoc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.tmaadminapp.Models.NocPresenterImplementer;
import com.example.tmaadminapp.Presenters.NocPresenter;
import com.example.tmaadminapp.R;
import com.example.tmaadminapp.Views.NocView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class BuildingNocActivity extends AppCompatActivity implements NocView
{

    private Toolbar mToolbar;
    private RecyclerView nocRecyclerList;
    private LinearLayoutManager layoutManager;
    private AdapterForNocRv adapter;
    private NocPresenter presenter;
    private DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building_noc);

        initViews();
        presenter.onGetAllNoc(dbRef);
    }

    private void initViews()
    {
        presenter = new NocPresenterImplementer(this);

        mToolbar = findViewById(R.id.noc_tool_bar);
        setTitle("Building Noc");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nocRecyclerList = findViewById(R.id.nocRecyclerView);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        nocRecyclerList.setLayoutManager(layoutManager);


        dbRef = FirebaseDatabase.getInstance().getReference();
    }

    // callback method to set recycler adapter
    @Override
    public void onSetNocRecyclerAdapter(List<ModelForNoc> nocList)
    {
        adapter = new AdapterForNocRv(nocList , this);
        nocRecyclerList.setAdapter(adapter);
    }
}
