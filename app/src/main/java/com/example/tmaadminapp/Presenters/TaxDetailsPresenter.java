package com.example.tmaadminapp.Presenters;

import android.net.Uri;

import com.google.firebase.database.DatabaseReference;

public interface TaxDetailsPresenter
{
    void addTaxDetails(DatabaseReference dbRef);

    void getDat(Uri imageUri);

    void getAllTaxesDetails();
}
