package com.example.tmaadminapp.Models;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.tmaadminapp.AppModules.WorkersListAndDetails.ModelForWorkerList;
import com.example.tmaadminapp.Presenters.AddWorkerPresenter;
import com.example.tmaadminapp.Views.AddWorkerView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddWorkerPresenterImplementer implements AddWorkerPresenter
{
    private AddWorkerView workerView;
    private List<ModelForWorkerList> list = new ArrayList<>();

    public AddWorkerPresenterImplementer(AddWorkerView workerView)
    {
        this.workerView = workerView;
    }

    @Override
    public void addWorker(DatabaseReference dbRef, String name, String phone, String cnic)
    {
        if(dbRef != null && !name.isEmpty() && !phone.isEmpty() && !cnic.isEmpty())
        {
            workerView.showProgressBar();

            DatabaseReference databaseReference = dbRef.push();
            Map dataMap = new HashMap<>();
            dataMap.put("nameOfWorker" , name);
            dataMap.put("phone" , phone);
            dataMap.put("cnic" , cnic);
            dataMap.put("average_rating" , "5.0");
            dataMap.put("total_reviews" , "0");
            dataMap.put("field" , "Sanitation");
            dataMap.put("pushKey" , databaseReference.getKey());

            databaseReference.setValue(dataMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task)
                {
                    if(task.isSuccessful())
                    {
                        workerView.hideProgressBar();
                        workerView.showMessage("Successfully register");
                    }
                    else
                    {
                        workerView.hideProgressBar();
                        workerView.showMessage("Fail to register worker");
                    }
                }
            });

        }

    }

    @Override
    public void getAllWorkers(DatabaseReference dbRef)
    {
       Query selectQuery =  dbRef.orderByChild("field").equalTo("Sanitation");

       selectQuery.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s)
            {

                ModelForWorkerList workerList = dataSnapshot.getValue(ModelForWorkerList.class);
                list.add(workerList);
                workerView.getWorkersList(list);

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

    @Override
    public void fabClick()
    {
      workerView.clickOnAddWorkerFab();
    }
}
