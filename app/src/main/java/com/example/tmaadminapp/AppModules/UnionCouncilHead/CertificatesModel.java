package com.example.tmaadminapp.AppModules.UnionCouncilHead;

import java.util.List;

public class CertificatesModel
{
    private String address , applicantCnic , applicantName , certificateType , childName,
    date , dateOfBirth, disability , districtOfBirth , doctorOrMideWife , fatherCnic , fatherName ,
    gender , grandFatherCnic , grandFatherName , motherCnic , motherName ,placeOfBirth , pushKey ,
    religion , relation , status , uid , unionCouncil , vaccinated;

    private String deceasedCnic,deceasedDateOfBirth ,deceasedName , gravyard, hubandCnic , husbandName;
    private List<String> cnicImages;


    public CertificatesModel() {}


    public String getDeceasedDateOfBirth() {
        return deceasedDateOfBirth;
    }

    public List<String> getCnicImages() {
        return cnicImages;
    }

    public String getAddress() {
        return address;
    }

    public String getApplicantCnic() {
        return applicantCnic;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public String getCertificateType() {
        return certificateType;
    }

    public String getChildName() {
        return childName;
    }

    public String getDate() {
        return date;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getDisability() {
        return disability;
    }

    public String getDistrictOfBirth() {
        return districtOfBirth;
    }

    public String getDoctorOrMideWife() {
        return doctorOrMideWife;
    }

    public String getFatherCnic() {
        return fatherCnic;
    }

    public String getFatherName() {
        return fatherName;
    }

    public String getGender() {
        return gender;
    }

    public String getGrandFatherCnic() {
        return grandFatherCnic;
    }

    public String getGrandFatherName() {
        return grandFatherName;
    }

    public String getMotherCnic() {
        return motherCnic;
    }

    public String getMotherName() {
        return motherName;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public String getPushKey() {
        return pushKey;
    }

    public String getReligion() {
        return religion;
    }

    public String getRelation() {
        return relation;
    }

    public String getStatus() {
        return status;
    }

    public String getUid() {
        return uid;
    }

    public String getUnionCouncil() {
        return unionCouncil;
    }

    public String getVaccinated() {
        return vaccinated;
    }

    public String getDeceasedCnic() {
        return deceasedCnic;
    }

    public String getDeceasedName() {
        return deceasedName;
    }

    public String getGravyard() {
        return gravyard;
    }

    public String getHubandCnic() {
        return hubandCnic;
    }

    public String getHusbandName() {
        return husbandName;
    }
}
