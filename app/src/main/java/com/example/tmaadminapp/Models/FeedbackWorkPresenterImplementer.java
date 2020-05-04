package com.example.tmaadminapp.Models;

import android.view.Display;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.tmaadminapp.AppModules.FeedbackWorks.ModelForFeedbackWorks;
import com.example.tmaadminapp.Presenters.FeedbackWorkPresenter;
import com.example.tmaadminapp.Views.FeedbackWorkView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.List;

public class FeedbackWorkPresenterImplementer implements FeedbackWorkPresenter
{
    private FeedbackWorkView workView;
    private List<ModelForFeedbackWorks> worksList  = new ArrayList<>();

    public FeedbackWorkPresenterImplementer(FeedbackWorkView workView) {
        this.workView = workView;
    }

    @Override
    public void getAllCompletedWork(DatabaseReference dbRef, String workerHeadKey)
    {
        if(dbRef != null && !workerHeadKey.isEmpty())
        {

            Query query = dbRef.orderByChild("uid").equalTo(workerHeadKey);

            query.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s)
                {
                   ModelForFeedbackWorks feedbackWorks = dataSnapshot.getValue(ModelForFeedbackWorks.class);
                   worksList.add(feedbackWorks);
                   workView.getAllFeedbackWorks(worksList);
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
