package com.example.tmaadminapp.Models;

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

public class CertificateDetailsPresenterImplementer implements CertificateDetailsPresenter
{

    private CertificateDetailsView certificateDetailsView;

    public CertificateDetailsPresenterImplementer(CertificateDetailsView certificateDetailsView) {
        this.certificateDetailsView = certificateDetailsView;
    }

    @Override
    public void onGetCertificateDetails(DatabaseReference dbRef, String refKey)
    {
        if(dbRef != null && !refKey.isEmpty())
        {

            dbRef.child("Certificates").addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    CertificatesModel value = dataSnapshot.getValue(CertificatesModel.class);

                    if(value.getCertificateType().equals("Birth"))
                    {
                        certificateDetailsView.showBirthCertificateData(value.getApplicantName(), value.getApplicantCnic(),
                                value.getFatherName(), value.getFatherCnic(), value.getMotherName(), value.getMotherCnic() ,
                                value.getGrandFatherName(), value.getGrandFatherCnic(), value.getDateOfBirth() ,
                                value.getDoctorOrMideWife(), value.getGender(), value.getRelation(), value.getReligion(),
                                value.getUnionCouncil(), value.getVaccinated(), value.getPlaceOfBirth(), value.getDisability(),
                                value.getDistrictOfBirth(), value.getAddress(), value.getChildName(), value.getCnicImages().get(0),
                                value.getCnicImages().get(1));
                    }
                    else
                    {
                        //certificateDetailsView.showDeathCertificateData();
                    }


                }
                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {}
                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {}
                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {}
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {}
            });


        }


    }
}
