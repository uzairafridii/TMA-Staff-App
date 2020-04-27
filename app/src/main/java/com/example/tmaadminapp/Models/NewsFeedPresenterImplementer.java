package com.example.tmaadminapp.Models;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.tmaadminapp.AppModules.NewsFeed.ModelForNewsFeed.NewsFeedModel;
import com.example.tmaadminapp.Presenters.NewsFeedPresenter;
import com.example.tmaadminapp.Views.NewsFeedView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

public class NewsFeedPresenterImplementer implements NewsFeedPresenter
{
    private NewsFeedView newsFeedView;
    private List<NewsFeedModel> list = new ArrayList<>();

    public NewsFeedPresenterImplementer(NewsFeedView newsFeedView) {
        this.newsFeedView = newsFeedView;
    }

    @Override
    public void getAllNewsFeed(DatabaseReference dbRef)
    {
        if(dbRef != null)
        {
            dbRef.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s)
                {

                    NewsFeedModel model = dataSnapshot.getValue(NewsFeedModel.class);
                    list.add(model);
                    newsFeedView.onSetNewsRecyclerAdapter(list);

                }
                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {}
                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {}
                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {}
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {}
            });
        }

    }
}
