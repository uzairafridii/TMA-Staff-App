package com.example.tmaadminapp.Administration.NewsFeedForAdmin;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.example.tmaadminapp.NewsFeed.ModelForNewsFeed.NewsFeedModel;
import com.example.tmaadminapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class NewsFeedForAdmin extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private Toolbar mToolbar;
    private LinearLayoutManager layoutManager;
    private ArrayList<NewsFeedModel> modelArrayList;
    private AdapterForAdminNewsFeedRecycler adapterForNewsFeed;
    private FloatingActionButton postNews;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_feed_for_admin);
        initViews();
        setItemsOfRecyclerView();
        mRecyclerView.setAdapter(adapterForNewsFeed);
    }

    private void initViews()
    {
        mRecyclerView = findViewById(R.id.news_feed_admin_recycler);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setStackFromEnd(true);
        layoutManager.setReverseLayout(true);
        mRecyclerView.setLayoutManager(layoutManager);


        // array list
        modelArrayList = new ArrayList<>();

        //Adapter of news feed list
        adapterForNewsFeed = new AdapterForAdminNewsFeedRecycler(modelArrayList , this);

        // tool bar
        mToolbar = findViewById(R.id.admin_news_feed_tool_bar);
        setTitle("News Feed");
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

    public void posetNewsFeedClickOnFab(View view)
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        View myView = LayoutInflater.from(this).inflate(R.layout.add_news_feed_by_admin, null);
        alert.setView(myView);

        AlertDialog dialog = alert.create();
        dialog.setCanceledOnTouchOutside(false);

        // TextView postTitle , postDescription;
        //  Button postBtn;

        dialog.show();

    }
}
