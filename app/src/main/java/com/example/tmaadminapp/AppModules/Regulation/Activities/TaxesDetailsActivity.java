package com.example.tmaadminapp.AppModules.Regulation.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.tmaadminapp.Models.TaxDetailsPresenterImp;
import com.example.tmaadminapp.Presenters.TaxDetailsPresenter;
import com.example.tmaadminapp.R;
import com.example.tmaadminapp.Views.TaxDetailsView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

public class TaxesDetailsActivity extends AppCompatActivity implements TaxDetailsView
{

    public static final int REQUEST_CODE = 10;
    private LinearLayout noItemFound;
    private Uri  imageUri;
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
        presenter = new TaxDetailsPresenterImp(this ,this);
        dbRef = FirebaseDatabase.getInstance().getReference();
    }

    // click on fab to add tax details
    public void addTaxDetailsBtnClick(View view)
    {
        presenter.addTaxDetails(dbRef);

    }


    // select image from gallery here
    @Override
    public void selectImage()
    {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent , REQUEST_CODE);

    }

    // get image from result here
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {

            CropImage.activity(imageUri)
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setAspectRatio(1, 1)
                    .start(this);

        }

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {

            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK)
            {
                 // send uri to presenter
                  Uri uri = result.getUri();
                  presenter.getDat(uri);

                Toast.makeText(this, ""+uri, Toast.LENGTH_SHORT).show();
            }

            }

    }

    @Override
    public void message(String msg) {
        Toast.makeText(this, msg , Toast.LENGTH_SHORT).show();
    }
}
