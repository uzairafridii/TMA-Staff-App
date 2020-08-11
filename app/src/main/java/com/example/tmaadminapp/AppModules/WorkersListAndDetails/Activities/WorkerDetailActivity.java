package com.example.tmaadminapp.AppModules.WorkersListAndDetails.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.tmaadminapp.AppModules.WorkersListAndDetails.Adapters.AdapterForTotalRatingRecycler;
import com.example.tmaadminapp.AppModules.WorkersListAndDetails.Models.ModelForWorkersRating;
import com.example.tmaadminapp.Models.WorkerDetailsPresenterImplementer;
import com.example.tmaadminapp.Presenters.WorkerDetailsPresenter;
import com.example.tmaadminapp.R;
import com.example.tmaadminapp.Views.WorkerDetailsView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class WorkerDetailActivity extends AppCompatActivity implements WorkerDetailsView
{

    private Toolbar mToolbar;
    private TextView workerName , workerAverageRating , workerGrade ;
    private RatingBar ratingBar;
    private String name , avgRating , workerKey;
    private RecyclerView workersReviewsRecyclerList;
    private LinearLayoutManager layoutManager;
    private AdapterForTotalRatingRecycler adapter;
    private WorkerDetailsPresenter presenter;
    private DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_detail);

        initViews();
        presenter.getAllReviews(dbRef , workerKey);

    }

    private void initViews()
    {
        presenter = new WorkerDetailsPresenterImplementer(this);

        // get name and key of worker from worker list
         name = getIntent().getStringExtra("name");
         workerKey = getIntent().getStringExtra("worker_key");
         avgRating = getIntent().getStringExtra("average_rating");

        mToolbar = findViewById(R.id.workerDetailToolBar);
        setTitle("Worker Details");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        workerName = findViewById(R.id.workerNameInDetail);
        workerAverageRating = findViewById(R.id.averageRatingOfWorker);
        workerGrade = findViewById(R.id.gradeOfRating);
        ratingBar = findViewById(R.id.workerRatingBarInDetail);

        workersReviewsRecyclerList = findViewById(R.id.workerCommentsAndRatingList);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setStackFromEnd(true);
        layoutManager.setReverseLayout(true);
        workersReviewsRecyclerList.setLayoutManager(layoutManager);

        dbRef = FirebaseDatabase.getInstance().getReference();


    }

    // set name and rating in text views
    @Override
    protected void onStart()
    {
        super.onStart();

        double number = Float.parseFloat(avgRating);
        double rating = (double)(Math.round( number * 100))/100.0;
        workerName.setText(name);
        workerAverageRating.setText("("+rating+")");
        ratingBar.setRating((float) rating);

        if(rating > 1 && rating <=2.5)
        {
            workerGrade.setText("Not Satisfied");
        }
        else if(rating > 2.5 && rating <=3.5)
        {
            workerGrade.setText("Average");
        }
        else if(rating > 3.5 && rating <=4.5)
        {
            workerGrade.setText("Excellent");
        }
        else if(rating > 4.5)
        {
            workerGrade.setText("Outstanding");
        }




    }


    // set reviews recycler adapter
    @Override
    public void onGetAllRatings(List<ModelForWorkersRating> ratingList)
    {
      adapter = new AdapterForTotalRatingRecycler(ratingList , this);
      workersReviewsRecyclerList.setAdapter(adapter);
    }
}
