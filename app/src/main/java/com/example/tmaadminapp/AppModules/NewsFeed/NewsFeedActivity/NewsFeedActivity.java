package com.example.tmaadminapp.AppModules.NewsFeed.NewsFeedActivity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;

import com.example.tmaadminapp.AppModules.NewsFeed.AdapterOfNewsFeed.AdapterForNewsFeed;
import com.example.tmaadminapp.AppModules.NewsFeed.ModelForNewsFeed.NewsFeedModel;
import com.example.tmaadminapp.R;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class NewsFeedActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager layoutManager;
    private ArrayList<NewsFeedModel> modelArrayList;
    private AdapterForNewsFeed adapterForNewsFeed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_feed);

        initViews();
        setItemsOfRecyclerView();

        mRecyclerView.setAdapter(adapterForNewsFeed);


    }

    private void initViews()
    {
        mRecyclerView  = findViewById(R.id.newsFeedRecyclerView);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setStackFromEnd(true);
        layoutManager.setReverseLayout(true);
        mRecyclerView.setLayoutManager(layoutManager);


        // array list
        modelArrayList = new ArrayList<>();

        //Adapter of news feed list
        adapterForNewsFeed = new AdapterForNewsFeed(modelArrayList , this);

        // tool bar
        mToolbar = findViewById(R.id.newsFeedToolBar);
        setTitle("News Feed");
        setTitleColor(Color.WHITE);
        setSupportActionBar(mToolbar);

        //action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void setItemsOfRecyclerView()
    {
        String date = DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());

        modelArrayList.add(new NewsFeedModel("First" , getString(R.string.dummy_text) , "By Admin",date ));
        modelArrayList.add(new NewsFeedModel("Second" , getString(R.string.dummy_text) , "By Clerk",date ));
        modelArrayList.add(new NewsFeedModel("Third" , getString(R.string.dummy_text) , "By Admin",date ));
        modelArrayList.add(new NewsFeedModel("Fourth" , getString(R.string.dummy_text) , "By Admin",date ));
        modelArrayList.add(new NewsFeedModel("Fifth" , getString(R.string.dummy_text) , "By Clerk",date ));
        modelArrayList.add(new NewsFeedModel("Sixth" , getString(R.string.dummy_text) , "By Admin",date ));
        modelArrayList.add(new NewsFeedModel("Seven" , getString(R.string.dummy_text) , "By Clerk",date ));
    }
}
