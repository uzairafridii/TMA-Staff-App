package com.example.tmaadminapp.Models;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.example.tmaadminapp.Presenters.TaxDetailsPresenter;
import com.example.tmaadminapp.R;
import com.google.firebase.database.DatabaseReference;

public class TaxDetailsPresenterImp implements TaxDetailsPresenter
{
    private Context context;

    public TaxDetailsPresenterImp(Context context) {
        this.context = context;
    }

    @Override
    public void addTaxDetails(DatabaseReference dbRef)
    {
        if(dbRef != null)
        {
            View myView = LayoutInflater.from(context).inflate(R.layout.add_tax_details_dialog, null);
            AlertDialog.Builder alert  = new AlertDialog.Builder(context);
            alert.setView(myView);
            Dialog dialog = alert.create();
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();





        }

    }
}
