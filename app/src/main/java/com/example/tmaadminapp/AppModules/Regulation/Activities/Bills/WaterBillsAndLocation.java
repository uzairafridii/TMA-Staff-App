package com.example.tmaadminapp.AppModules.Regulation.Activities.Bills;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.tmaadminapp.Models.BillPresenterImplementer;
import com.example.tmaadminapp.Presenters.BillPresenter;
import com.example.tmaadminapp.R;
import com.example.tmaadminapp.Views.BillView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageActivity;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.util.List;

public class WaterBillsAndLocation extends AppCompatActivity implements BillView {

    public static final int REQUEST_CODE = 2;
    private Toolbar mToolbar;
    private LinearLayout noItemFound;
    private RecyclerView waterBillsList;
    private BillPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_bills_and_location);
        setTitle("Water Bills");

        initViews();

        presenter.getAllUserConnections();

    }

    private void initViews() {
        waterBillsList = findViewById(R.id.rvBills);
        waterBillsList.setLayoutManager(new LinearLayoutManager(this));
        noItemFound = findViewById(R.id.noBillFound);

        mToolbar = findViewById(R.id.billToolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        presenter = new BillPresenterImplementer(this, this);

    }


    //fab button click listener
    public void addNewConnection(View view) {
        presenter.addNewConnection();

    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideLayout() {
        noItemFound.setVisibility(View.GONE);
    }

    @Override
    public void showLayout() {
        noItemFound.setVisibility(View.VISIBLE);
    }

    @Override
    public void getAllConnectionList(List<BillsModel> list) {
        BillsRecyclerAdapter adapter = new BillsRecyclerAdapter(list, this, this);
        waterBillsList.setAdapter(adapter);
    }

    @Override
    public void getRefNo(String refNo) {
        presenter.addCitizenBill(refNo);

    }

    @Override
    public void selectImage() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == requestCode && resultCode == RESULT_OK) {
            // send uri to presenter
            Uri uri = data.getData();
            presenter.getUriOfImage(uri);

        }


    }
}
