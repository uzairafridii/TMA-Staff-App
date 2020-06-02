package com.example.tmaadminapp.AppModules.UnionCouncilHead;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tmaadminapp.Models.CertificatePresenterImplementer;
import com.example.tmaadminapp.Presenters.CertificatesPresenter;
import com.example.tmaadminapp.R;
import com.example.tmaadminapp.Views.CertificatesView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class UnionCouncil extends AppCompatActivity implements CertificatesView
{

    private Toolbar mToolbar;
    private TextView noCertificateText;
    private RecyclerView certificateRecycler;
    private LinearLayoutManager layoutManager;
    private AdapterForCertificatesRecycler adapter;
    private CertificatesPresenter presenter;
    private DatabaseReference dbRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_union_council);
        setTitle("Certificates");

        initViews();
        presenter.onGetCertificates(dbRef);
    }

    private void initViews()
    {
        presenter = new CertificatePresenterImplementer(this);
        mToolbar = findViewById(R.id.union_council_tool_bar);
        setSupportActionBar(mToolbar);


        noCertificateText = findViewById(R.id.noCertificateText);
        certificateRecycler = findViewById(R.id.certificateRecycler);
        layoutManager  = new LinearLayoutManager(this);
        layoutManager.setStackFromEnd(true);
        layoutManager.setReverseLayout(true);
        certificateRecycler.setLayoutManager(layoutManager);

        dbRef = FirebaseDatabase.getInstance().getReference();


    }

    @Override
    public void getCertificates(List<CertificatesModel> list)
    {
        adapter = new AdapterForCertificatesRecycler(this , list , this);
        certificateRecycler.setAdapter(adapter);
    }

    @Override
    public void hideTextView()
    {
        noCertificateText.setVisibility(View.INVISIBLE);

    }

    @Override
    public void showMessage(String message)
    {
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}
