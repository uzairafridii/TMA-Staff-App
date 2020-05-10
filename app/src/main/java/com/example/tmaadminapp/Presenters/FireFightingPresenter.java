package com.example.tmaadminapp.Presenters;

import com.google.firebase.database.DatabaseReference;

public interface FireFightingPresenter
{
    void getFireRequestList(DatabaseReference dbRef);

    void getDriverDetails(DatabaseReference dbRef);

    void onAddDriverDetails(DatabaseReference dbRef);
}
