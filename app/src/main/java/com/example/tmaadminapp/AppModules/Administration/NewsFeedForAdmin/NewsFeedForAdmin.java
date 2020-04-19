package com.example.tmaadminapp.AppModules.Administration.NewsFeedForAdmin;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tmaadminapp.AppModules.NewsFeed.ModelForNewsFeed.NewsFeedModel;
import com.example.tmaadminapp.Models.AdminNewsFeedPresenterImplementer;
import com.example.tmaadminapp.R;
import com.example.tmaadminapp.Views.AdminNewsView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class NewsFeedForAdmin extends AppCompatActivity implements AdminNewsView
{
    private RecyclerView mRecyclerView;
    private Toolbar mToolbar;
    private LinearLayoutManager layoutManager;
    private AdapterForAdminNewsFeedRecycler adapterForNewsFeed;
    private ProgressDialog progressDialog;
    private TextView postTitle , postDescription;
    private String date , authorName , title , description;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private AdminNewsFeedPresenterImplementer newsFeedPresenterImplementer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_feed_for_admin);
        initViews();

    }

    private void initViews()
    {
        newsFeedPresenterImplementer = new AdminNewsFeedPresenterImplementer(this);

        //firebase
        databaseReference = FirebaseDatabase.getInstance().getReference().child("NewsFeed");
        firebaseAuth = FirebaseAuth.getInstance();

        // recycler view
        mRecyclerView = findViewById(R.id.news_feed_admin_recycler);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setStackFromEnd(true);
        layoutManager.setReverseLayout(true);
        mRecyclerView.setLayoutManager(layoutManager);

        // tool bar
        mToolbar = findViewById(R.id.admin_news_feed_tool_bar);
        setTitle("News Feed");
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        progressDialog = new ProgressDialog(this , R.style.MyAlertDialogStyle);


    }

    // fab click
    public void posetNewsFeedClickOnFab(View view)
    {
        newsFeedPresenterImplementer.showNewsPostingDialog();
    }

    // call backs method of newsfeed view
    @Override
    public void onShowProgressBar() {

        progressDialog.setMessage("Please wait...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

    }

    @Override
    public void onHideProgressBar() {
        progressDialog.dismiss();
    }

    @Override
    public void onShowMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showPostinNewsForm() {
        showPostDialog();
    }

    @Override
    public void onGetAllNews(List<NewsFeedModel> newsFeedModelList) {

        adapterForNewsFeed  = new AdapterForAdminNewsFeedRecycler(newsFeedModelList ,getApplicationContext() ,
                this , databaseReference);
        mRecyclerView.setAdapter(adapterForNewsFeed);

    }

    @Override
    protected void onStart() {
        super.onStart();
        newsFeedPresenterImplementer.getAllNews(databaseReference);
    }

    // news posting form dialog
    private void showPostDialog()
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        View myView = LayoutInflater.from(this).inflate(R.layout.add_news_feed_by_admin, null);
        alert.setView(myView);

        final AlertDialog dialog = alert.create();
        dialog.setCanceledOnTouchOutside(false);

        postTitle = myView.findViewById(R.id.news_title_in_admin);
        postDescription = myView.findViewById(R.id.news_description_in_admin);

        myView.findViewById(R.id.post_news_feed_submit)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        title = postTitle.getText().toString();
                        description = postDescription.getText().toString();
                        date = DateFormat.getDateInstance().format(Calendar.getInstance().getTime());
                        authorName = "Admin";

                        newsFeedPresenterImplementer.addNews(databaseReference,title , description , authorName , date );
                        dialog.dismiss();


                    }
                });


        dialog.show();
    }

}
