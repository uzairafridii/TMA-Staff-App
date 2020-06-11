package com.example.tmaadminapp.Models;

import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.example.tmaadminapp.AppModules.Administration.AdminStaffManagement.WorkerHeadList.ModelForWorkerHead;
import com.example.tmaadminapp.Presenters.WorkersHeadPresenter;
import com.example.tmaadminapp.R;
import com.example.tmaadminapp.Views.WorkerHeadView;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorkersHeadPresenterImplementer implements WorkersHeadPresenter , AdapterView.OnItemSelectedListener
{
    private WorkerHeadView workerHeadView;
    private Context context;
    private List<ModelForWorkerHead> workerHeadList = new ArrayList<>();
    private String department;

    public WorkersHeadPresenterImplementer(WorkerHeadView workerHeadView , Context context)
    {
        this.workerHeadView = workerHeadView;
        this.context = context;
    }


    // to show signUp  dialog
    @Override
    public void signUpWorkerHead(DatabaseReference dbRef , FirebaseAuth auth)
    {
        signUpFormDialog(dbRef , auth);

    }

    // to get all workers head
    @Override
    public void getAllWorkersHead(final DatabaseReference databaseReference) {


        databaseReference.child("WorkersHead").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                ModelForWorkerHead workerHead = dataSnapshot.getValue(ModelForWorkerHead.class);
                workerHeadList.add(workerHead);

                // send worker head list to recycler adapter
                workerHeadView.onGetAllWorkerHeadDetails(workerHeadList);

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


    // custom signup dialog method
    private void signUpFormDialog(final DatabaseReference dbRef , final FirebaseAuth mAuth)
    {
        String[] departmentList = {"Sanitation", "Regulation", "Infrastructure","Union Council" , "Finance", "Head Clerk"};
        View customView = LayoutInflater.from(context).inflate(R.layout.workers_head_sign_up, null);
        final AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setView(customView);

        // initialize edittext fields
         final EditText name , phone , email , password;
        name = customView.findViewById(R.id.worker_head_name);
        phone = customView.findViewById(R.id.worker_head_phone_no);
        email = customView.findViewById(R.id.worker_head_email);
        password = customView.findViewById(R.id.worker_head_password);

        Spinner spinner = customView.findViewById(R.id.worker_head_dept);
        ArrayAdapter adapter  = new ArrayAdapter(context, android.R.layout.simple_list_item_1, departmentList);
        spinner.setOnItemSelectedListener(this);
        spinner.setAdapter(adapter);


        // set click on register button
        customView.findViewById(R.id.registerWorkerHeadButton)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        if (dbRef != null && mAuth != null && !name.getText().toString().isEmpty() &&
                                !phone.getText().toString().isEmpty() && !department.isEmpty() &&
                                !email.getText().toString().isEmpty() && !password.getText().toString().isEmpty())
                        {

                            workerHeadView.onShowProgressBar();

                            // sign up the workers head with email
                            mAuth.createUserWithEmailAndPassword(email.getText().toString() , password.getText().toString())
                                    .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {

                                    //to get device token
                                    FirebaseInstanceId.getInstance().getInstanceId()
                                            .addOnSuccessListener(new OnSuccessListener<InstanceIdResult>() {
                                        @Override
                                        public void onSuccess(InstanceIdResult instanceIdResult) {


                                            // get token
                                            String deviceToken = instanceIdResult.getToken();
                                            Log.d("deviceTokenByAdmin", "onSuccess: "+deviceToken);

                                            //TODO : token id is left to store in database
                                            // set data in map
                                            final Map<String, String> workerHeadData = new HashMap<>();
                                            workerHeadData.put("name_worker_head", name.getText().toString());
                                            workerHeadData.put("phone", phone.getText().toString());
                                            workerHeadData.put("department", department);
                                            workerHeadData.put("password", password.getText().toString());
                                            workerHeadData.put("email", email.getText().toString());
                                            workerHeadData.put("token", "null for this time");
                                            workerHeadData.put("uid", mAuth.getCurrentUser().getUid());

                                            // store data in firebase database
                                            dbRef.child("WorkersHead").child(mAuth.getCurrentUser().getUid())
                                                    .setValue(workerHeadData).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {

                                                    workerHeadView.onHideProgressBar();
                                                    workerHeadView.showMessage("SuccessFully register");

                                                }
                                            });
                                        }
                                    });
                                }});


                        } else {
                            workerHeadView.showMessage("Please all fields are require");
                        }







                        // dismiss dialog
                        alert.setOnDismissListener(new DialogInterface.OnDismissListener() {
                            @Override
                            public void onDismiss(DialogInterface dialogInterface) {

                                dialogInterface.dismiss();
                            }
                        });


                    }
                });




        alert.show();
    }


    // spinner item click listener
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
    {
       department = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {}
}
