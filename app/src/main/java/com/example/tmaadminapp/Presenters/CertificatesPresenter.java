package com.example.tmaadminapp.Presenters;

import com.google.firebase.database.DatabaseReference;

public interface CertificatesPresenter
{

    void onGetCertificates(DatabaseReference dbRef);
}
