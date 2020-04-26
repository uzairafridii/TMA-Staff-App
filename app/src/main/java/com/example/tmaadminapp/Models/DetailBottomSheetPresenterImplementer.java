package com.example.tmaadminapp.Models;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.tmaadminapp.Presenters.DetailBottomSheetPresenter;
import com.example.tmaadminapp.Views.DetailBottomSheetView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class DetailBottomSheetPresenterImplementer implements DetailBottomSheetPresenter
{
    private DetailBottomSheetView detailBottomSheetView;

    public DetailBottomSheetPresenterImplementer(DetailBottomSheetView detailBottomSheetView) {
        this.detailBottomSheetView = detailBottomSheetView;
    }

    @Override
    public void getWorkerDetails(DatabaseReference dbRef, String key)
    {

        if(dbRef != null && !key.isEmpty())
        {

            Query query = dbRef.child(key);

            query.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                {
                     if(dataSnapshot.hasChildren())
                     {

                         String name = (String) dataSnapshot.child("name_worker_head").getValue();
                         String phone = (String) dataSnapshot.child("phone").getValue();
                         String email = (String) dataSnapshot.child("email").getValue();
                         String dept = (String) dataSnapshot.child("department").getValue();

                         Log.d("nameOfUser", "onDataChange: "+name);


                         detailBottomSheetView.onSetInTextView(name , phone , email , dept);

                     }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {}

            });

        }

    }
}
