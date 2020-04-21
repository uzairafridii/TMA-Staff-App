package com.example.tmaadminapp.Models;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.tmaadminapp.AppModules.Administration.AdminStaffManagement.WorkerHeadList.ModelForWorkerHead;
import com.example.tmaadminapp.Presenters.WorkersHeadPresenter;
import com.example.tmaadminapp.Views.WorkerHeadView;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorkersHeadPresenterImplementer implements WorkersHeadPresenter
{
    private WorkerHeadView workerHeadView;
    private List<ModelForWorkerHead> workerHeadList = new ArrayList<>();

    public WorkersHeadPresenterImplementer(WorkerHeadView workerHeadView) {
        this.workerHeadView = workerHeadView;
    }

    @Override
    public void onAddWorkerHeadDetails(final DatabaseReference dbRef, final FirebaseAuth mAuth, final String name, final String phone,
                                       final String department, final String email, final String password)
    {
        if (dbRef != null && mAuth != null && !name.isEmpty() &&
                !phone.isEmpty() && !department.isEmpty() &&
                !email.isEmpty() && !password.isEmpty())
        {

            workerHeadView.onShowProgressBar();

            // sign up the workers head with email
            mAuth.createUserWithEmailAndPassword(email , password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {

                    //to get device token
                    FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(new OnSuccessListener<InstanceIdResult>() {
                        @Override
                        public void onSuccess(InstanceIdResult instanceIdResult) {

                            DatabaseReference databaseReference = dbRef.child("Workers Head").push();

                            // get token and pushkey
                            String deviceToken = instanceIdResult.getToken();
                            Log.d("deviceTokenByAdmin", "onSuccess: "+deviceToken);
                            String pushKey = databaseReference.getRef().getKey();

                            // set data in map
                            final Map<String, String> workerHeadData = new HashMap<>();
                            workerHeadData.put("name_worker_head", name);
                            workerHeadData.put("phone", phone);
                            workerHeadData.put("department", department);
                            workerHeadData.put("password", password);
                            workerHeadData.put("email", email);
                            workerHeadData.put("pushKey", pushKey);
                            workerHeadData.put("token", "null for this time");
                            workerHeadData.put("uid", mAuth.getCurrentUser().getUid());

                            // store data in firebase database
                            databaseReference.setValue(workerHeadData).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {

                                    workerHeadView.onHideProgressBar();
                                    workerHeadView.showMessage("SuccessFully register");
                                    workerHeadView.onClearAllFields();

                                }
                            });


                        }
                    });



                }
            });




        } else {
            workerHeadView.showMessage("Please all fields are require");
        }

    }

    // to show signUp  dialog
    @Override
    public void showSignUpDialog() {
        workerHeadView.onShowSignUpDialog();
    }

    // to get all workers head
    @Override
    public void getAllWorkersHead(final DatabaseReference databaseReference) {


        databaseReference.child("Workers Head").addChildEventListener(new ChildEventListener() {
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
}
