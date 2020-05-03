package com.example.tmaadminapp.Presenters;

import com.google.firebase.database.DatabaseReference;

public interface ComplaintsPresenter
{
    void getTotalComplaints(DatabaseReference dbRef , String field);
}
