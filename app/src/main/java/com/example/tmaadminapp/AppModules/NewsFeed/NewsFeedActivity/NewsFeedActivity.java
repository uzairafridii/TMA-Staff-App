package com.example.tmaadminapp.AppModules.NewsFeed.NewsFeedActivity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.tmaadminapp.AppModules.NewsFeed.AdapterOfNewsFeed.AdapterForNewsFeed;
import com.example.tmaadminapp.AppModules.NewsFeed.ModelForNewsFeed.NewsFeedModel;
import com.example.tmaadminapp.Models.NewsFeedPresenterImplementer;
import com.example.tmaadminapp.Presenters.NewsFeedPresenter;
import com.example.tmaadminapp.R;
import com.example.tmaadminapp.Views.NewsFeedView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class NewsFeedActivity extends AppCompatActivity implements NewsFeedView
{
    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private LinearLayout noItemFound;
    private LinearLayoutManager layoutManager;
    private AdapterForNewsFeed adapterForNewsFeed;
    private NewsFeedPresenter presenter;
    private DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_feed);

        initViews();
    }

    private void initViews()
    {
        presenter = new NewsFeedPresenterImplementer(this);

        mRecyclerView  = findViewById(R.id.newsFeedRecyclerView);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setStackFromEnd(true);
        layoutManager.setReverseLayout(true);
        mRecyclerView.setLayoutManager(layoutManager);

        // tool bar
        mToolbar = findViewById(R.id.newsFeedToolBar);
        setTitle("News Feed");
        setSupportActionBar(mToolbar);

        noItemFound = findViewById(R.id.noItemFoundLayout);

        dbRef = FirebaseDatabase.getInstance().getReference().child("NewsFeed");

        presenter.getAllNewsFeed(dbRef);
    }


    @Override
    public void onSetNewsRecyclerAdapter(List<NewsFeedModel> list)
    {
       adapterForNewsFeed = new AdapterForNewsFeed(list , this, this);
       mRecyclerView.setAdapter(adapterForNewsFeed);
    }

    @Override
    public void hideLayout() {
        noItemFound.setVisibility(View.GONE);
    }

    @Override
    public void showLayout() {
      noItemFound.setVisibility(View.VISIBLE);
    }
}
