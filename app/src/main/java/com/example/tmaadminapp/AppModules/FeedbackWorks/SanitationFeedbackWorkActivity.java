package com.example.tmaadminapp.AppModules.FeedbackWorks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.tmaadminapp.Models.FeedbackWorkPresenterImplementer;
import com.example.tmaadminapp.Presenters.FeedbackWorkPresenter;
import com.example.tmaadminapp.R;
import com.example.tmaadminapp.Views.FeedbackWorkView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class SanitationFeedbackWorkActivity extends AppCompatActivity implements FeedbackWorkView
{

    private Toolbar mToolbar;
    private RecyclerView feedBackRecyclerView;
    private LinearLayoutManager layoutManager;
    private AdapterForFeedbackWorksRecycler adapter;
    private FeedbackWorkPresenter workPresenter;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_work);

        initViews();
        workPresenter.getAllCompletedWork(databaseReference , firebaseAuth.getCurrentUser().getUid());

    }

    private void initViews()
    {
         workPresenter = new FeedbackWorkPresenterImplementer(this);

        mToolbar = findViewById(R.id.feedbackWorkToolbar);
        setSupportActionBar(mToolbar);
        setTitle("Feedback Works");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        feedBackRecyclerView = findViewById(R.id.feebackWorkRecycler);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        feedBackRecyclerView.setLayoutManager(layoutManager);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Feedback Work");
        firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void getAllFeedbackWorks(List<ModelForFeedbackWorks> list)
    {
       adapter = new AdapterForFeedbackWorksRecycler(list , this);
       feedBackRecyclerView.setAdapter(adapter);
    }
}
