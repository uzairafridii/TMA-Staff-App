package com.example.tmaadminapp.Models;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.tmaadminapp.AppModules.Administration.AdminStaffManagement.WorkerListInAdminPage.ModelForWorkerLIstInAdmin;
import com.example.tmaadminapp.Presenters.AdminWorkerListPresenter;
import com.example.tmaadminapp.Views.AdminWorkersListView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

public class AdminWorkersListPresenterImplementer implements AdminWorkerListPresenter
{
    private AdminWorkersListView adminWorkersListView;
    private List<ModelForWorkerLIstInAdmin> workersList = new ArrayList<>();

    public AdminWorkersListPresenterImplementer(AdminWorkersListView adminWorkersListView)
    {
        this.adminWorkersListView = adminWorkersListView;
    }

    @Override
    public void onGetWorkersList(DatabaseReference dbRef)
    {

        if(dbRef != null)
        {

            dbRef.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s)
                {
                    ModelForWorkerLIstInAdmin workerLIstInAdmin = dataSnapshot.getValue(ModelForWorkerLIstInAdmin.class);
                    workersList.add(workerLIstInAdmin);
                    adminWorkersListView.getWorkerList(workersList);
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
