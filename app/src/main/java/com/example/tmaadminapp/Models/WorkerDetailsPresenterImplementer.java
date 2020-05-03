package com.example.tmaadminapp.Models;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.tmaadminapp.AppModules.WorkersListAndDetails.ModelForWorkersRating;
import com.example.tmaadminapp.Presenters.WorkerDetailsPresenter;
import com.example.tmaadminapp.Views.WorkerDetailsView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class WorkerDetailsPresenterImplementer implements WorkerDetailsPresenter
{
     private WorkerDetailsView workerView;
     private List<ModelForWorkersRating> workersRatingList = new ArrayList<>();


    public WorkerDetailsPresenterImplementer(WorkerDetailsView workerView) {
        this.workerView = workerView;
    }

    @Override
    public void getAllReviews(final DatabaseReference dbRef, final String workerKey)
    {

        if(dbRef != null && !workerKey.isEmpty())
        {

            Query query = dbRef.child("Ratings").orderByChild("worker_id").equalTo(workerKey);

            query.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s)
                {
                    final ModelForWorkersRating workersRating = dataSnapshot.getValue(ModelForWorkersRating.class);

                    // getting user name who add rating
                   String userKey = workersRating.getUser_id();
                   DatabaseReference userRef = dbRef.child("TMA Lachi").child("Users").child(userKey);
                   userRef.addValueEventListener(new ValueEventListener() {
                       @Override
                       public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                       {
                          String userName = dataSnapshot.child("user_name").getValue().toString();

                          workersRating.setUserName(userName);

                          // add items in list and send to recycler adapter
                          workersRatingList.add(workersRating);
                          workerView.onGetAllRatings(workersRatingList);


                       }
                       @Override
                       public void onCancelled(@NonNull DatabaseError databaseError) {}
                   });



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
