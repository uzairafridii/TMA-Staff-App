package com.example.tmaadminapp.Presenters;

import com.google.firebase.database.DatabaseReference;

public interface WorkerDetailsPresenter
{

    void getAllReviews(DatabaseReference dbRef , String workerKey);

}
