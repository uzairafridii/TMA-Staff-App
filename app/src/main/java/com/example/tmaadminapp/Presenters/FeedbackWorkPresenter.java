package com.example.tmaadminapp.Presenters;

import com.google.firebase.database.DatabaseReference;

public interface FeedbackWorkPresenter
{
     void getAllCompletedWork(DatabaseReference dbRef , String workerHeadKey);
}
