package com.example.tmaadminapp.Presenters;

import com.google.firebase.database.DatabaseReference;

public interface CertificateDetailsPresenter
{

    void onGetCertificateDetails(DatabaseReference dbRef , String refKey);

}
