package com.example.tmaadminapp.Presenters;

import com.example.tmaadminapp.AppModules.Administration.AdminStaffManagement.WorkerHeadList.ModelForWorkerHead;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

public interface WorkersHeadPresenter
{
    void signUpWorkerHead(DatabaseReference dbRef, FirebaseAuth mAutheErr);

    void getAllWorkersHead(DatabaseReference databaseReference);

}
