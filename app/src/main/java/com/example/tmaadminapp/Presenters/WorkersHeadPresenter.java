package com.example.tmaadminapp.Presenters;

import com.example.tmaadminapp.AppModules.Administration.AdminStaffManagement.WorkerHeadList.ModelForWorkerHead;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

public interface WorkersHeadPresenter
{
    void onAddWorkerHeadDetails(DatabaseReference dbRef, FirebaseAuth mAuth, String name, String phone,
                                String department, String email, String password);

    void showSignUpDialog();

    void getAllWorkersHead(DatabaseReference databaseReference);

}
