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
import androidx.annotation.Nullable;

import com.example.tmaadminapp.AppModules.Regulation.Activities.Tax.TaxModel;
import com.example.tmaadminapp.Presenters.TaxDetailsPresenter;
import com.example.tmaadminapp.R;
import com.example.tmaadminapp.Views.TaxDetailsView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaxDetailsPresenterImp implements TaxDetailsPresenter
{
    private Context context;
    private TaxDetailsView taxDetailsView;
    private ImageView imageView;
    private Uri mImageUri;
    private List<TaxModel> list = new ArrayList<>();

    public TaxDetailsPresenterImp(Context context, TaxDetailsView taxDetailsView) {
        this.context = context;
        this.taxDetailsView = taxDetailsView;
    }

    @Override
    public void addTaxDetails(final DatabaseReference dbRef)
    {
        if(dbRef != null)
        {
            // inflate custom dialog
            View myView = LayoutInflater.from(context).inflate(R.layout.add_tax_details_dialog, null);
            AlertDialog.Builder alert  = new AlertDialog.Builder(context);
            alert.setView(myView);

            final Dialog dialog = alert.create();
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();


            // initialize custom dialog views
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

            // click on add tax button ==> send data to firebase
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

                                        // get url and data then send to firebase
                                        String downloadUrl = uri.toString();

                                        // get push id to reference the post with that id
                                        DatabaseReference ref = dbRef.push();
                                        String pushKey = ref.getRef().getKey();

                                        Map<String, String> taxData = new HashMap<>();
                                        taxData.put("taxTitle", taxTitle);
                                        taxData.put("taxAmount", price);
                                        taxData.put("imageUrl", downloadUrl);
                                        taxData.put("postId", pushKey);

                                        // send data to firebase here
                                       ref.setValue(taxData).addOnSuccessListener(new OnSuccessListener<Void>() {
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
        // set preview of image here in dialog
        mImageUri = imageUri;
        imageView.setImageURI(mImageUri);
        Log.d("ImageUri", "getDat: "+imageUri);
    }

    @Override
    public void getAllTaxesDetails()
    {

        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("TaxDetails");
        dbRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s)
            {

                TaxModel taxModel = dataSnapshot.getValue(TaxModel.class);
                list.add(taxModel);
                taxDetailsView.showList(list);


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
