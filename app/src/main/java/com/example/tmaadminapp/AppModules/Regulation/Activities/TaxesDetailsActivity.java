package com.example.tmaadminapp.AppModules.Regulation.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.tmaadminapp.R;

public class TaxesDetailsActivity extends AppCompatActivity {

    private LinearLayout noItemFound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taxes_details);
        setTitle("Taxes Details");
    }
}
