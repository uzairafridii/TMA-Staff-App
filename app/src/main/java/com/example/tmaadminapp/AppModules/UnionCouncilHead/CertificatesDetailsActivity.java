package com.example.tmaadminapp.AppModules.UnionCouncilHead;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.tmaadminapp.Models.CertificateDetailsPresenterImplementer;
import com.example.tmaadminapp.Presenters.CertificateDetailsPresenter;
import com.example.tmaadminapp.R;
import com.example.tmaadminapp.Views.CertificateDetailsView;
import com.example.tmaadminapp.Views.CertificatesView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class CertificatesDetailsActivity extends AppCompatActivity implements CertificateDetailsView
{

    private String certificateKey;
    private Toolbar toolbar;
    private ImageView frontImage , backImage;
    private TextView applicantName, applicantCnic, fatherName , fatherCnic , motherName , motherCnic,
                     relation , religion, placeOfBirth , unionCouncil, dateOfBirth;

    private TextView childNameText , districtOfBirthText, addressText, disabilityText , doctOrMideWifeText,
                     vaccinatedText , genderText , grandFatherNameText , grandFatherCnicText;

    private TextView childNameValue , districtOfBirthValue, addressValue, disabilityValue , doctOrMideWifeValue,
                     vaccinatedValue , genderValue , grandFatherNameValue , grandFatherCnicValue;


    private DatabaseReference dbRef;
    private CertificateDetailsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certificates_details);

        initViews();
        presenter.onGetCertificateDetails(dbRef , certificateKey);
    }

    private void initViews()
    {
        presenter = new CertificateDetailsPresenterImplementer(this);
        dbRef = FirebaseDatabase.getInstance().getReference();
        certificateKey = getIntent().getStringExtra("refKey");

        toolbar = findViewById(R.id.certificateDetailsToolbar);
        setSupportActionBar(toolbar);
        setTitle("Certificate Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        frontImage = findViewById(R.id.cnicFront);
        backImage = findViewById(R.id.cnicBack);


        childNameText = findViewById(R.id.childNameTextInDetals);
        childNameValue = findViewById(R.id.childNameValueInDetals);
        districtOfBirthText = findViewById(R.id.districtOfBirthTextInDetals);
        districtOfBirthValue = findViewById(R.id.districtOfBirthValueInDetals);
        addressText = findViewById(R.id.addressTextInDetals);
        addressValue = findViewById(R.id.addressValueInDetals);
        disabilityText = findViewById(R.id.disabilityTextInDetals);
        disabilityValue = findViewById(R.id.disabilityValueInDetals);
        doctOrMideWifeText = findViewById(R.id.doctorOrMidewifeTextInDetals);
        doctOrMideWifeValue = findViewById(R.id.doctorOrMidewifeValueInDetals);
        vaccinatedText = findViewById(R.id.vaccinatedTextInDetals);
        vaccinatedValue = findViewById(R.id.vaccinatedValueInDetals);
        vaccinatedValue = findViewById(R.id.vaccinatedValueInDetals);
        grandFatherNameText = findViewById(R.id.grandFatherNameTextInDetals);
        grandFatherNameValue = findViewById(R.id.grandFatherNameValueInDetals);
        grandFatherCnicText = findViewById(R.id.grandFatherCnicTextInDetals);
        grandFatherCnicValue = findViewById(R.id.grandFatherCnicValueInDetals);
        genderValue = findViewById(R.id.genderValueInDetals);
        genderText = findViewById(R.id.genderTextInDetals);

        applicantName = findViewById(R.id.applicantNameValueInDetals);
        applicantCnic = findViewById(R.id.applicantCnicValueInDetals);
        fatherName = findViewById(R.id.fatherNameValueInDetals);
        fatherCnic = findViewById(R.id.fatherCnicValueInDetals);
        motherName = findViewById(R.id.motherNameValueInDetals);
        motherCnic = findViewById(R.id.motherCnicValueInDetals);
        relation = findViewById(R.id.relationValueInDetals);
        religion = findViewById(R.id.religionValueInDetals);
        placeOfBirth = findViewById(R.id.placeOfBirthValueInDetals);
        unionCouncil = findViewById(R.id.ucValueInDetals);
        dateOfBirth = findViewById(R.id.dateOfBirthValueInDetals);


    }


    @Override
    public void showDeathCertificateData(CertificatesModel deathCertificate)
    {

        applicantName.setText(deathCertificate.getApplicantName());
        applicantCnic.setText(deathCertificate.getApplicantCnic());
        fatherName.setText(deathCertificate.getFatherName());
        fatherCnic.setText(deathCertificate.getFatherCnic());
        motherName.setText(deathCertificate.getMotherName());
        motherCnic.setText(deathCertificate.getMotherCnic());
        relation.setText(deathCertificate.getRelation());
        religion.setText(deathCertificate.getReligion());
        placeOfBirth.setText(deathCertificate.getPlaceOfBirth());
        unionCouncil.setText(deathCertificate.getUnionCouncil());
        dateOfBirth.setText(deathCertificate.getDeceasedDateOfBirth());


        int width = 0, height = 0;
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width , height);
        childNameText.setLayoutParams(layoutParams);
        childNameValue.setLayoutParams(layoutParams);
        districtOfBirthText.setLayoutParams(layoutParams);
        districtOfBirthValue.setLayoutParams(layoutParams);
        addressText.setLayoutParams(layoutParams);
        addressValue.setLayoutParams(layoutParams);
        genderValue.setLayoutParams(layoutParams);
        genderText.setLayoutParams(layoutParams);


        vaccinatedValue.setText(deathCertificate.getGravyard());
        vaccinatedText.setText("Graveyard : ");
        grandFatherNameValue.setText(deathCertificate.getDeceasedName());
        grandFatherNameText.setText("Deceased Name : ");
        grandFatherCnicValue.setText(deathCertificate.getDeceasedCnic());
        grandFatherCnicText.setText("Deceased Cnic : ");
        disabilityValue.setText(deathCertificate.getHusbandName());
        disabilityText.setText("Husband/Wife Name : ");
        doctOrMideWifeValue.setText(deathCertificate.getHusbandCnic());
        doctOrMideWifeText.setText("Husband/Wife Cnic : ");
    }

    @Override
    public void showBirthCertificateData(CertificatesModel birthCertificate)
    {

        childNameValue.setText(birthCertificate.getChildName());
        applicantName.setText(birthCertificate.getApplicantName());
        applicantCnic.setText(birthCertificate.getApplicantCnic());
        fatherName.setText(birthCertificate.getFatherName());
        fatherCnic.setText(birthCertificate.getFatherCnic());
        motherName.setText(birthCertificate.getMotherName());
        motherCnic.setText(birthCertificate.getMotherCnic());
        grandFatherNameValue.setText(birthCertificate.getGrandFatherName());
        grandFatherCnicValue.setText(birthCertificate.getGrandFatherCnic());
        relation.setText(birthCertificate.getRelation());
        religion.setText(birthCertificate.getReligion());
        vaccinatedValue.setText(birthCertificate.getVaccinated());
        placeOfBirth.setText(birthCertificate.getPlaceOfBirth());
        unionCouncil.setText(birthCertificate.getUnionCouncil());
        dateOfBirth.setText(birthCertificate.getDateOfBirth());
        disabilityValue.setText(birthCertificate.getDisability());
        doctOrMideWifeValue.setText(birthCertificate.getDoctorOrMideWife());
        districtOfBirthValue.setText(birthCertificate.getDistrictOfBirth());
        addressValue.setText(birthCertificate.getAddress());
        genderValue.setText(birthCertificate.getGender());


        Glide.with(this).load(birthCertificate.getCnicImages().get(0)).into(frontImage);
        Glide.with(this).load(birthCertificate.getCnicImages().get(1)).into(backImage);
    }

}
