package com.example.tmaadminapp.Models;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.tmaadminapp.Presenters.TaxDetailsPresenter;
import com.example.tmaadminapp.R;
import com.example.tmaadminapp.Views.TaxDetailsView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;

public class TaxDetailsPresenterImp implements TaxDetailsPresenter
{
    private Context context;
    private TaxDetailsView taxDetailsView;
    private ImageView imageView;
    private Uri mImageUri;

    public TaxDetailsPresenterImp(Context context, TaxDetailsView taxDetailsView) {
        this.context = context;
        this.taxDetailsView = taxDetailsView;
    }

    @Override
    public void addTaxDetails(DatabaseReference dbRef)
    {
        if(dbRef != null)
        {
            View myView = LayoutInflater.from(context).inflate(R.layout.add_tax_details_dialog, null);
            AlertDialog.Builder alert  = new AlertDialog.Builder(context);
            alert.setView(myView);

            final Dialog dialog = alert.create();
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();


            imageView = myView.findViewById(R.id.taxLogo);
            final ImageButton addImage  = myView.findViewById(R.id.addTaxLogo);
            final EditText title = myView.findViewById(R.id.taxTitle);
            final EditText taxAmount = myView.findViewById(R.id.taxPrice);
            final ProgressBar progressBar = myView.findViewById(R.id.progressBar);
            Button addTaxDetails = myView.findViewById(R.id.addNowTaxBtn);


            // click on add image button
            addImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    taxDetailsView.selectImage();


                }
            });

            // click on add tax button
            addTaxDetails.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    final String taxTitle = title.getText().toString();
                    final String price = taxAmount.getText().toString();

                    if (!taxTitle.isEmpty() && !price.isEmpty() && mImageUri != null) {


                        progressBar.setVisibility(View.VISIBLE);
                        // image storage ref where store image
                        final StorageReference filePath = FirebaseStorage.getInstance().getReference()
                                .child("TaxesLogo/" + mImageUri.getLastPathSegment());

                        // put file in storage ref
                        filePath.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                                filePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {

                                        String downloadUrl = uri.toString();

                                        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference()
                                                .child("TaxDetails");

                                        Map<String, String> taxData = new HashMap<>();
                                        taxData.put("taxTitle", taxTitle);
                                        taxData.put("taxAmount", price);
                                        taxData.put("imageUrl", downloadUrl);

                                        dbRef.push().setValue(taxData).addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {

                                                progressBar.setVisibility(View.INVISIBLE);
                                                taxDetailsView.message("Successfully added");
                                                dialog.dismiss();


                                            }
                                        });


                                    }
                                });

                            }
                        });
                    }
                    else
                    {
                        taxDetailsView.message("Please title, amount and image is required");
                    }
                }
            });



        }

    }



    @Override
    public void getDat(Uri imageUri)
    {
        mImageUri = imageUri;
        imageView.setImageURI(mImageUri);
        Log.d("ImageUri", "getDat: "+imageUri);
    }
}
