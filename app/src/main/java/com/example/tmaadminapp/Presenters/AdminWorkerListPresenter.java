package com.example.tmaadminapp.Presenters;

import com.google.firebase.database.DatabaseReference;

public interface AdminWorkerListPresenter
{
    void onGetWorkersList(DatabaseReference dbRef);
}
