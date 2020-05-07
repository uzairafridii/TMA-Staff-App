package com.example.tmaadminapp.Models;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.tmaadminapp.AppModules.InfrastructureHead.BuildingNoc.ModelForNoc;
import com.example.tmaadminapp.Presenters.NocPresenter;
import com.example.tmaadminapp.Views.NocView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class NocPresenterImplementer implements NocPresenter
{
    private NocView nocView;
    private List<ModelForNoc>  nocList = new ArrayList<>();

    public NocPresenterImplementer(NocView nocView) {
        this.nocView = nocView;
    }

    @Override
    public void onGetAllNoc(final DatabaseReference dbRef)
    {
        if(dbRef != null)
        {
            // get users noc details
            dbRef.child("Noc").addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s)
                {
                    // get noc values from noc nodes
                    final ModelForNoc nocModel = dataSnapshot.getValue(ModelForNoc.class);

                    // get user name
                    DatabaseReference userNameRef = dbRef.child("TMA Lachi").child("Users").child(nocModel.getUid());
                    userNameRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                        {
                           String userName = dataSnapshot.child("user_name").getValue().toString();

                          // set , add and send data to recycler adapter
                           nocModel.setUserName(userName);
                           nocList.add(nocModel);
                           nocView.onSetNocRecyclerAdapter(nocList);

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
