package com.example.tmaadminapp.Presenters;

import android.net.Uri;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public interface AddCompletedWorkPresenter
{
    void submitData(DatabaseReference dbRef, StorageReference storageRef, FirebaseAuth auth,
                    String pushKey, String title, List<String> workerList, List<Uri> imagesUri);

    void selectImage();

    void setRecyclerAdapter();
}
