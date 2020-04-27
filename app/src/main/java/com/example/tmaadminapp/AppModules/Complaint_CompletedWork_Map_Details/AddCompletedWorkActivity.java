package com.example.tmaadminapp.AppModules.Complaint_CompletedWork_Map_Details;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tmaadminapp.Models.AddCompletedWorkPresenterImplementer;
import com.example.tmaadminapp.Presenters.AddCompletedWorkPresenter;
import com.example.tmaadminapp.R;
import com.example.tmaadminapp.Views.AddCompletedWorkView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class AddCompletedWorkActivity extends AppCompatActivity implements AddCompletedWorkView
{
    public static final int REQUEST_CODE = 1;
    private String title , pushKey , uid;
    private RecyclerView recyclerView;
    private GridLayoutManager layoutManager;
    private AdapterForSelectedImageRv adapter;
    private Toolbar mToolbar;
    private EditText editTextFirstWorker, editTextSecondWorker;
    private List<Uri> imageUriList;
    private List<String> workerList;
    private AddCompletedWorkPresenter workPresenter;
    private ProgressDialog progressDialog;
    private DatabaseReference dbRef;
    private StorageReference storageRef;
    private FirebaseAuth userAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_completed_work);

        initViews();

    }

    private void initViews()
    {
        workPresenter = new AddCompletedWorkPresenterImplementer(this);

        title = getIntent().getStringExtra("title");
        uid = getIntent().getStringExtra("uid");
        pushKey = getIntent().getStringExtra("pushKey");

        Log.d("ComplaintData", "initViews: "+title+"\n"+uid+"\n"+pushKey);

        recyclerView = findViewById(R.id.selectedImagesRv);
        layoutManager = new GridLayoutManager(this , 3);
        recyclerView.setLayoutManager(layoutManager);

        imageUriList = new ArrayList<>();
        workerList = new ArrayList<>();

        mToolbar = findViewById(R.id.completedWorkToolbar);
        setSupportActionBar(mToolbar);
        setTitle("Add Completed Work");

        editTextFirstWorker = findViewById(R.id.firstWorkerName);
        editTextSecondWorker = findViewById(R.id.secondWorkerName);

        progressDialog = new ProgressDialog(this , R.style.MyAlertDialogStyle);


        userAuth = FirebaseAuth.getInstance();
        dbRef = FirebaseDatabase.getInstance().getReference().child("Feedback Work");
        storageRef = FirebaseStorage.getInstance().getReference().child("Complaints").child("Completed Work Images");

    }

    // click on upload image
    public void uploadWorkImages(View view)
    {
        workPresenter.selectImage();
    }

    // click on submit work
    public void submitWork(View view)
    {
       workerList.add(editTextFirstWorker.getText().toString());
       workerList.add(editTextSecondWorker.getText().toString());

       workPresenter.submitData(dbRef ,storageRef , userAuth , pushKey , title ,workerList, imageUriList);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //check condition for Gallery image picker request code
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {

            ClipData clipData = data.getClipData();
            if (clipData != null) {
                for (int i = 0; i < clipData.getItemCount(); i++) {
                    //add images to arraylist
                    imageUriList.add(clipData.getItemAt(i).getUri());
                    workPresenter.setRecyclerAdapter();

                }

            }
            else
                {
                Uri uri = data.getData();
                imageUriList.add(uri);
                workPresenter.setRecyclerAdapter();
            }
        }
    }

    // add completed work call backs methods
    @Override
    public void showProgressBar()
    {
      progressDialog.setMessage("Please wait...");
      progressDialog.setCancelable(false);
      progressDialog.setCanceledOnTouchOutside(false);
      progressDialog.show();
    }

    @Override
    public void hideProgressBar()
    {
      progressDialog.dismiss();
    }

    @Override
    public void showMessage(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void clearAllFields()
    {
        editTextFirstWorker.setText("");
        editTextSecondWorker.setText("");
        imageUriList.clear();
        workerList.clear();
    }

    @Override
    public void onSelectImage()
    {
        imageUriList.clear();
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    public void onSetAdapter()
    {
         adapter = new AdapterForSelectedImageRv(imageUriList , this);
         recyclerView.setAdapter(adapter);
    }
}
