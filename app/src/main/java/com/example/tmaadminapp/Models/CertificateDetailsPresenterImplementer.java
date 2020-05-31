package com.example.tmaadminapp.Models;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.tmaadminapp.AppModules.Complaints.ModelForComplaints;
import com.example.tmaadminapp.AppModules.UnionCouncilHead.CertificatesModel;
import com.example.tmaadminapp.Presenters.CertificateDetailsPresenter;
import com.example.tmaadminapp.Views.CertificateDetailsView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class CertificateDetailsPresenterImplementer implements CertificateDetailsPresenter
{

    private CertificateDetailsView certificateDetailsView;

    public CertificateDetailsPresenterImplementer(CertificateDetailsView certificateDetailsView) {
        this.certificateDetailsView = certificateDetailsView;
    }

    @Override
    public void onGetCertificateDetails(DatabaseReference dbRef, String refKey)
    {
        if(dbRef != null && !refKey.isEmpty()) {

            // get the certificate details
            dbRef.child("Certificates").child(refKey).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    CertificatesModel value = dataSnapshot.getValue(CertificatesModel.class);

                    if (value.getCertificateType().equals("Birth")) {
                        certificateDetailsView.showBirthCertificateData(value);

                    } else if (value.getCertificateType().equals("Death")) {
                        certificateDetailsView.showDeathCertificateData(value);
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {}
            });

        }

    }
}
