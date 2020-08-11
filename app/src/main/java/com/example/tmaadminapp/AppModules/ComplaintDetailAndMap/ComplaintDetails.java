package com.example.tmaadminapp.AppModules.ComplaintDetailAndMap;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.borjabravo.readmoretextview.ReadMoreTextView;
import com.example.tmaadminapp.Models.ComplaintDetailsPresenterImplementer;
import com.example.tmaadminapp.Presenters.ComplaintDetailsPresenter;
import com.example.tmaadminapp.R;
import com.example.tmaadminapp.Views.ComplaintDetailsView;

import java.util.ArrayList;

public class ComplaintDetails extends AppCompatActivity implements ComplaintDetailsView
{

    private String title, description;
    private ArrayList<String> list;
    private RecyclerView recyclerView;
    private GridLayoutManager layoutManager;
    private AdapterForDetailComplaintImagesRecycler adapter;
    private TextView tvTitle;
    private ReadMoreTextView tvDescription;
    private ComplaintDetailsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint_details);

       initViews();

       presenter.setImagesAdapter();
       presenter.setTitleAndDesc();

    }

    private void initViews()
    {
        presenter = new ComplaintDetailsPresenterImplementer(this);

        title = getIntent().getStringExtra("title");
        description = getIntent().getStringExtra("description");
        list = getIntent().getStringArrayListExtra("imageUrls");

        recyclerView = findViewById(R.id.complaintDetailRv);
        layoutManager = new GridLayoutManager(this , 2);
        recyclerView.setLayoutManager(layoutManager);

        tvTitle = findViewById(R.id.titleOfComplaint);
        tvDescription = findViewById(R.id.descriptionInDetail);

    }

    @Override
    public void setTitleAndDescription() {

        tvTitle.setText(title);
        tvDescription.setText(description);
    }

    @Override
    public void onSetAdapter() {

        adapter = new AdapterForDetailComplaintImagesRecycler(this , list);
        recyclerView.setAdapter(adapter);
    }
}
