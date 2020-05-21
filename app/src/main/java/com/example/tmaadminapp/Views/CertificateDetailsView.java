package com.example.tmaadminapp.Views;

public interface CertificateDetailsView
{

    void showDeathCertificateData(String name , String cnic , String fatherName , String fatherCnic, String motherName,
                                  String motherCnic ,String relation , String religion, String uc, String placeOfBirth,
                                  String graveyard, String deceasedName , String deceasedCnic , String dateOfBirth,
                                  String husbandName , String husbandCnic);

    void showBirthCertificateData(String name , String cnic , String fatherName , String fatherCnic, String motherName,
                                  String motherCnic , String gFatherName , String gFatherCnic , String dateOfBirth,
                                  String doctorMidWife , String gender , String relation , String religion,
                                  String uc , String vaccinated , String placeOfBirth , String disability,
                                  String districtOfBirth, String address , String childName , String fronCnic , String backCnic);

}
