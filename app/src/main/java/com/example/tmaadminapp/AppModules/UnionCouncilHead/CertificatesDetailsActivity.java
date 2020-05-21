package com.example.tmaadminapp.AppModules.UnionCouncilHead;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.TextView;

import com.example.tmaadminapp.R;

public class CertificatesDetailsActivity extends AppCompatActivity {

    private String certificateKey;
    private Toolbar toolbar;
    private TextView applicantName, applicantCnic, fatherName , fatherCnic , motherName , motherCnic,
                     relation , religion, placeOfBirth , unionCouncil, dateOfBirth;

    private TextView childNameText , districtOfBirthText, addressText, disabilityText , doctOrMideWifeText,
                     vaccinatedText , genderText , grandFatherNameText , grandFatherCnicText;

    private TextView childNameValue , districtOfBirthValue, addressValue, disabilityValue , doctOrMideWifeValue,
                     vaccinatedValue , genderValue , grandFatherNameValue , grandFatherCnicValue;

    private TextView graveyard, deceasedName , deceasedCnic , husbandName , husbandCnic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certificates_details);

        initViews();
    }

    private void initViews()
    {
        certificateKey = getIntent().getStringExtra("refKey");

        toolbar = findViewById(R.id.certificateDetailsToolbar);
        setSupportActionBar(toolbar);
        setTitle("Certificate Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


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

}
