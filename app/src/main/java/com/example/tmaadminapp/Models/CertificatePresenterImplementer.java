package com.example.tmaadminapp.Models;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.tmaadminapp.AppModules.UnionCouncilHead.CertificatesModel;
import com.example.tmaadminapp.Presenters.CertificatesPresenter;
import com.example.tmaadminapp.Views.CertificatesView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

public class CertificatePresenterImplementer implements CertificatesPresenter
{
    private CertificatesView certificatesView;
    private List<CertificatesModel> list = new ArrayList<>();

    public CertificatePresenterImplementer(CertificatesView certificatesView) {
        this.certificatesView = certificatesView;
    }

    @Override
    public void onGetCertificates(DatabaseReference dbRef)
    {
        if(dbRef != null)
        {
            dbRef.child("Certificates").addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s)
                {
                    CertificatesModel model = dataSnapshot.getValue(CertificatesModel.class);
                    list.add(model);
                    certificatesView.getCertificates(list);

                }
                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {}
                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {}
                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) { }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) { }
            });
        }
        else
        {
            certificatesView.showMessage("No database found");
        }

    }
}
