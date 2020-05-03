package com.example.tmaadminapp.Presenters;
import com.google.firebase.database.DatabaseReference;


public interface AddWorkerPresenter
{

    void addWorker(DatabaseReference dbRef , String name , String phone , String cnic);

    void getAllWorkers(DatabaseReference dbRef);

    void fabClick();
}
