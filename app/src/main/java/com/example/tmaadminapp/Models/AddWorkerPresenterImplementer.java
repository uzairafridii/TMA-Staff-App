package com.example.tmaadminapp.Models;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.tmaadminapp.AppModules.WorkersListAndDetails.ModelForWorkerList;
import com.example.tmaadminapp.Presenters.AddWorkerPresenter;
import com.example.tmaadminapp.R;
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
    private Context context;
    private List<ModelForWorkerList> list = new ArrayList<>();

    public AddWorkerPresenterImplementer(AddWorkerView workerView , Context context)
    {
        this.workerView = workerView;
        this.context = context;
    }


    @Override
    public void getAllWorkers(DatabaseReference dbRef , String field)
    {
       Query selectQuery =  dbRef.orderByChild("field").equalTo(field);

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
    public void fabClick(DatabaseReference dbRef)
    {
        addWorkerDialogForm(dbRef);
    }

    private void addWorkerDialogForm(final DatabaseReference dbRef )
    {
        View customView =  LayoutInflater.from(context).inflate(R.layout.add_worker_dialog_layout, null);
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setView(customView);

        final AlertDialog dialog = alert.create();
        dialog.setCanceledOnTouchOutside(false);

        // initialize editext views
        final EditText workerName , workerPhoneNo, workerCnic;
        workerName  = customView.findViewById(R.id.worker_name);
        workerPhoneNo  = customView.findViewById(R.id.worker_phone_no);
        workerCnic  = customView.findViewById(R.id.worker_cnic);

        // click on add worker button
        customView.findViewById(R.id.add_worker_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                if(dbRef != null && !workerName.getText().toString().isEmpty() &&
                        !workerPhoneNo.getText().toString().isEmpty() && !workerCnic.getText().toString().isEmpty())
                {
                    workerView.showProgressBar();

                    DatabaseReference databaseReference = dbRef.push();
                    Map dataMap = new HashMap<>();
                    dataMap.put("nameOfWorker" , workerName.getText().toString());
                    dataMap.put("phone" , workerPhoneNo.getText().toString());
                    dataMap.put("cnic" , workerCnic.getText().toString());
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




                dialog.dismiss();
            }
        });


        dialog.show();

    }
}
