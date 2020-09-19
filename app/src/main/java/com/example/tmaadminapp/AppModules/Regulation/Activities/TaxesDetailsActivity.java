package com.example.tmaadminapp.AppModules.Regulation.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.tmaadminapp.Models.TaxDetailsPresenterImp;
import com.example.tmaadminapp.Presenters.TaxDetailsPresenter;
import com.example.tmaadminapp.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TaxesDetailsActivity extends AppCompatActivity {

    private LinearLayout noItemFound;
    private ListView taxDetailsList;
    private TaxDetailsPresenter presenter;
    private DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taxes_details);
        setTitle("Taxes Details");

        initViews();
    }

    private void initViews()
    {
        taxDetailsList = findViewById(R.id.taxesDetailsListView);
        presenter = new TaxDetailsPresenterImp(this);
        dbRef = FirebaseDatabase.getInstance().getReference();
    }

    // click on fab to add tax details
    public void addTaxDetailsBtnClick(View view)
    {
        presenter.addTaxDetails(dbRef);

    }
}
