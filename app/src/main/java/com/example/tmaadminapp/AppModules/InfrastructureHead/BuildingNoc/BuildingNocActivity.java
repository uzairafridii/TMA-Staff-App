package com.example.tmaadminapp.AppModules.InfrastructureHead.BuildingNoc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.tmaadminapp.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BuildingNocActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private RecyclerView nocRecyclerList;
    private LinearLayoutManager layoutManager;
    private DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building_noc);

        initViews();
    }

    private void initViews()
    {
        mToolbar = findViewById(R.id.noc_tool_bar);
        setTitle("Building Noc");
        setSupportActionBar(mToolbar);

        nocRecyclerList = findViewById(R.id.nocRecyclerView);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        nocRecyclerList.setLayoutManager(layoutManager);


        dbRef = FirebaseDatabase.getInstance().getReference();
    }
}
