package com.example.tmaadminapp.Models;

import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.tmaadminapp.AppModules.InfrastructureHead.FireFighting.ModelForFireFighting;
import com.example.tmaadminapp.Presenters.FireFightingPresenter;
import com.example.tmaadminapp.R;
import com.example.tmaadminapp.Views.FireFightingView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FireFightingPresenterImplementer implements FireFightingPresenter
{

    private List<ModelForFireFighting> fireFightingList = new ArrayList<>();
    private FireFightingView fireFightingView;
    private Context context;

    public FireFightingPresenterImplementer(FireFightingView fireFightingView, Context context) {
        this.fireFightingView = fireFightingView;
        this.context = context;
    }

    @Override
    public void getFireRequestList(DatabaseReference dbRef)
    {
        if(dbRef != null) {
            // reference to fire brigade request node
            dbRef.child("Fire Fighting").child("Fire Brigade Request")
                    .addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                            final ModelForFireFighting fireFighting = dataSnapshot.getValue(ModelForFireFighting.class);
                            // add to list and send to adapter
                            fireFightingList.add(fireFighting);
                            fireFightingView.setAdapter(fireFightingList);

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
    }

    @Override
    public void getDriverDetails(DatabaseReference dbRef)
    {
          if(dbRef != null)
          {
              // to get the driver name and phone
              dbRef.child("Fire Fighting").child("Driver Details")
                      .addValueEventListener(new ValueEventListener() {
                          @Override
                          public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                          {
                              if(dataSnapshot.hasChildren()) {

                                  String driverName = dataSnapshot.child("driverName").getValue().toString();
                                  String driverPhone = dataSnapshot.child("driverPhone").getValue().toString();
                                  String driverCnic = dataSnapshot.child("driverCnic").getValue().toString();

                                  // set name and phone no in textviews
                                  fireFightingView.setDriverDetails(driverName, driverPhone, driverCnic);
                              }
                              else
                              {
                                  fireFightingView.showMessage("No driver record found");
                              }
                          }
                          @Override
                          public void onCancelled(@NonNull DatabaseError databaseError) {}
                      });
          }

    }

    @Override
    public void onAddDriverDetails(final DatabaseReference dbRef)
    {
        // set custom view dialog form to add driver details
        View customView =  LayoutInflater.from(context).inflate(R.layout.add_worker_dialog_layout, null);
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setView(customView);

        final AlertDialog dialog = alert.create();
        dialog.setCanceledOnTouchOutside(false);

        // initialize edittext and text views
        TextView textView = customView.findViewById(R.id.workerTxt);
        textView.setText("Add Driver Details");

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
                    fireFightingView.showProgressBar();

                    DatabaseReference databaseReference = dbRef.child("Fire Fighting").child("Driver Details");

                    Map dataMap = new HashMap<>();
                    dataMap.put("driverName" , workerName.getText().toString());
                    dataMap.put("driverPhone" , workerPhoneNo.getText().toString());
                    dataMap.put("driverCnic" , workerCnic.getText().toString());

                    databaseReference.setValue(dataMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task)
                        {
                            if(task.isSuccessful())
                            {
                                fireFightingView.hideProgressBar();
                                fireFightingView.showMessage("Successfully register");
                            }
                            else
                            {
                                fireFightingView.hideProgressBar();
                                fireFightingView.showMessage("Fail to register worker");
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
