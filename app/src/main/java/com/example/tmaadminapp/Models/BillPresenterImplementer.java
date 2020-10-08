package com.example.tmaadminapp.Models;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.tmaadminapp.AppModules.Regulation.Activities.Bills.BillsModel;
import com.example.tmaadminapp.Presenters.BillPresenter;
import com.example.tmaadminapp.R;
import com.example.tmaadminapp.Views.BillView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BillPresenterImplementer implements BillPresenter {
    private BillView billView;
    private Context context;
    private ImageView imageView;
    private Uri imageUri;
    private List<BillsModel> lIst = new ArrayList<>();

    public BillPresenterImplementer(BillView view, Context context) {
        this.billView = view;
        this.context = context;
    }


    @Override
    public void addNewConnection() {

        final DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("Water_Bills");

        final View billLayout = LayoutInflater.from(context).inflate(R.layout.add_new_water_connection, null);
        final AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setView(billLayout);
        alert.show();

        final Dialog dialog = alert.create();
        dialog.setCanceledOnTouchOutside(false);

        final EditText ownerName, connectionLocation, refNo;
        Button addConnectionBtn;
        final ProgressBar progressBar;

        ownerName = billLayout.findViewById(R.id.ownerName);
        connectionLocation = billLayout.findViewById(R.id.connectionAddress);
        refNo = billLayout.findViewById(R.id.referenceNo);
        addConnectionBtn = billLayout.findViewById(R.id.addConnectionButton);
        progressBar = billLayout.findViewById(R.id.progressBar);

        addConnectionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {


                if (!ownerName.getText().toString().isEmpty() && !connectionLocation.getText().toString().isEmpty()
                        && !refNo.getText().toString().isEmpty()) {
                    progressBar.setVisibility(View.VISIBLE);

                    String date = DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());

                    Map<String, String> connectionData = new HashMap<>();
                    connectionData.put("ownerName", ownerName.getText().toString());
                    connectionData.put("connectionAddress", ownerName.getText().toString());
                    connectionData.put("connectionDate", date);
                    connectionData.put("billMonth", "null");
                    connectionData.put("bill_image", "default");
                    connectionData.put("refNo", refNo.getText().toString());


                    dbRef.child(refNo.getText().toString())
                            .setValue(connectionData)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if (task.isSuccessful()) {
                                        billView.showMessage("Add new connection");
                                        progressBar.setVisibility(View.GONE);
                                        dialog.dismiss();

                                    } else {
                                        billView.showMessage(task.getException().getMessage());
                                        progressBar.setVisibility(View.GONE);
                                        dialog.dismiss();
                                    }

                                }
                            });


                } else {
                    billView.showMessage("Please enter owner name and connection address");
                }


            }
        });


    }

    @Override
    public void getAllUserConnections() {
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("Water_Bills");

        dbRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                BillsModel model = dataSnapshot.getValue(BillsModel.class);
                lIst.add(model);

                billView.getAllConnectionList(lIst);


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

    @Override
    public void addCitizenBill(final String refNo) {
        View myView = LayoutInflater.from(context).inflate(R.layout.bill_image_layout, null);
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setView(myView);


        final Dialog dialog = alert.create();
        dialog.setCanceledOnTouchOutside(false);

        dialog.show();

        ImageButton imageButton;
        Button addNow;
        final ProgressBar progressBar;

        imageView = myView.findViewById(R.id.billImage);
        imageButton = myView.findViewById(R.id.addBillImage);
        addNow = myView.findViewById(R.id.addBillBtn);
        progressBar = myView.findViewById(R.id.progressBar);


        // click to select image
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                billView.selectImage();
            }
        });


        // click on add Bill to firebase button
        addNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressBar.setVisibility(View.VISIBLE);
                final DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("Water_Bills").child(refNo);
                final StorageReference dbStore = FirebaseStorage.getInstance().getReference().child("Water_Bills").child(imageUri.getLastPathSegment());

                Log.d("uri", "onClick: " + imageUri);

                dbStore.putFile(imageUri)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                                dbStore.getDownloadUrl()
                                        .addOnSuccessListener(new OnSuccessListener<Uri>() {
                                            @Override
                                            public void onSuccess(Uri uri) {

                                                Log.d("url", "onSuccess: " + uri.toString());

                                                String date = DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
                                                Map<String, Object> data = new HashMap<>();

                                                data.put("bill_image", uri.toString());
                                                data.put("billMonth", date);

                                                dbRef.updateChildren(data)
                                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                            @Override
                                                            public void onSuccess(Void aVoid) {

                                                                progressBar.setVisibility(View.GONE);
                                                                billView.showMessage("Successfully added");
                                                                dialog.dismiss();

                                                            }
                                                        });


                                            }
                                        });


                            }
                        });


            }
        });


    }

    @Override
    public void getUriOfImage(Uri uri) {
        imageUri = uri;
        imageView.setImageURI(uri);
    }
}
