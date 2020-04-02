package com.example.tmaadminapp.UnionCouncilHead;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.tmaadminapp.R;

public class UnionCouncil extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_union_council);
    }

    public void clickOnBirthCertificatesCard(View view)
    {
        startActivity(new Intent(this , UcMain.class));
    }
}
