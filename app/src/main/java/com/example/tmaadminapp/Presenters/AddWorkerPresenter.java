package com.example.tmaadminapp.Presenters;
import com.google.firebase.database.DatabaseReference;


public interface AddWorkerPresenter
{
    void getAllWorkers(DatabaseReference dbRef , String workersDept);

    void fabClick(DatabaseReference databaseReference, String workerDept);

}
