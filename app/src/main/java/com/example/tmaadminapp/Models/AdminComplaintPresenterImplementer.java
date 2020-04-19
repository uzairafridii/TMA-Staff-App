package com.example.tmaadminapp.Models;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.tmaadminapp.AppModules.Administration.AdminStaffManagement.ComplaintsListForAdmin.ModelForTotalComplaints;
import com.example.tmaadminapp.Presenters.AdminComplaintPreseneter;
import com.example.tmaadminapp.Views.AdminCompaintView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

public class AdminComplaintPresenterImplementer
        implements AdminComplaintPreseneter
{
    private AdminCompaintView complaintView;
    private List<ModelForTotalComplaints> totalComplaintsList = new ArrayList<>();

    public AdminComplaintPresenterImplementer(AdminCompaintView complaintView) {
        this.complaintView = complaintView;
    }


    @Override
    public void getAllComplaints(final DatabaseReference databaseReference)
    {
        if(databaseReference != null)
        {

            databaseReference.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s)
                {
                    // get all data from firebase
                    ModelForTotalComplaints totalComplaints = dataSnapshot.getValue(ModelForTotalComplaints.class);
                    totalComplaintsList.add(totalComplaints);

                    // send list to adapter
                    complaintView.onGetComplaints(totalComplaintsList);
                }
                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }
                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                }
                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }
        else
        {
            complaintView.showErrorMessage("Database reference not found");
        }

    }


}
