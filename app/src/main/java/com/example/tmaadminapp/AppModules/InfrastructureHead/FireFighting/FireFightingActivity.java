package com.example.tmaadminapp.AppModules.InfrastructureHead.FireFighting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tmaadminapp.Models.FireFightingPresenterImplementer;
import com.example.tmaadminapp.Presenters.FireFightingPresenter;
import com.example.tmaadminapp.R;
import com.example.tmaadminapp.Views.FireFightingView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class FireFightingActivity extends AppCompatActivity implements FireFightingView
{
    private Toolbar mToolbar;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private AdapterForFightingRecycler adapter;
    private TextView driverName , driverPhone , driverCnic;
    private ProgressDialog progressDialog;
    private FireFightingPresenter firePresenter;
    private DatabaseReference dbRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fire_fighting);

        initViews();
        firePresenter.getDriverDetails(dbRef);
        firePresenter.getFireRequestList(dbRef);

    }


    private void initViews()
    {
        firePresenter = new FireFightingPresenterImplementer(this , this);

        mToolbar = findViewById(R.id.fireFightingToolbar);
        setSupportActionBar(mToolbar);
        setTitle("Fire Fighting");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.fireFightingRequestList);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);

        driverName = findViewById(R.id.driverNameInFireFightingActivity);
        driverPhone = findViewById(R.id.driverPhoneNoInFireFightingActivity);
        driverCnic = findViewById(R.id.driverCnic);

        progressDialog = new ProgressDialog(this , R.style.MyAlertDialogStyle);

        dbRef = FirebaseDatabase.getInstance().getReference();
    }

    // fab button click
    public void fabAddDriverDetails(View view)
    {
          firePresenter.onAddDriverDetails(dbRef);
    }


    // callback methods of fire fighting view
    @Override
    public void setAdapter(List<ModelForFireFighting> fireList)
    {
         adapter = new AdapterForFightingRecycler(fireList , this);
         recyclerView.setAdapter(adapter);
    }

    @Override
    public void setDriverDetails(String name, String phone , String cnic)
    {
         driverName.setText(name);
         driverPhone.setText(phone);
         driverCnic.setText(cnic);
    }

    @Override
    public void showProgressBar()
    {
       progressDialog.setMessage("Add Driver Details");
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
}
