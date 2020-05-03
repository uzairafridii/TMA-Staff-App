package com.example.tmaadminapp.Models;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.tmaadminapp.AppModules.Complaints.ModelForComplaints;
import com.example.tmaadminapp.Presenters.ComplaintsPresenter;
import com.example.tmaadminapp.Views.ComplaintsView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ComplaintsPresenterImplementer implements ComplaintsPresenter
{
    private ComplaintsView complaintsView;
    private List<ModelForComplaints> complaintsList = new ArrayList<>();

    public ComplaintsPresenterImplementer(ComplaintsView complaintsView) {
        this.complaintsView = complaintsView;
    }

    @Override
    public void getTotalComplaints(DatabaseReference dbRef , String field)
    {
        if(dbRef != null)
        {
            // query to get only sanitation complaints
            Query query = dbRef.orderByChild("field").equalTo(field);

            query.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s)
                {

                    // get user complaints details
                    final ModelForComplaints complaints = dataSnapshot.getValue(ModelForComplaints.class);

                    // users ref to get username who complaint
                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("TMA Lachi").child("Users")
                            .child(complaints.getUid());

                    ref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            // get the user name
                            String name = dataSnapshot.child("user_name").getValue().toString();

                            // set user name in setter of complaint model
                            complaints.setName(name);
                            Log.d("userNameInComplaint", "onDataChange: " + name+"/n Field"+complaints.getField());

                            complaintsList.add(complaints);
                            // send list to adapter
                            complaintsView.onGetAllSanitationComplaints(complaintsList);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
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
