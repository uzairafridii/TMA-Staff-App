package com.example.tmaadminapp.Models;

import android.net.Uri;

import androidx.annotation.NonNull;

import com.example.tmaadminapp.Presenters.AddCompletedWorkPresenter;
import com.example.tmaadminapp.Views.AddCompletedWorkView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddCompletedWorkPresenterImplementer implements AddCompletedWorkPresenter
{
    private AddCompletedWorkView workView;
    private int counter = 0;
    private List<String> imageUrls = new ArrayList<>();

    public AddCompletedWorkPresenterImplementer(AddCompletedWorkView workView) {
        this.workView = workView;
    }

    @Override
    public void submitData(final DatabaseReference dbRef, StorageReference storageRef, final FirebaseAuth auth,
                           final String pushKey , final String title, final List<String> workerList,
                           final List<Uri> imagesUri)
    {

        if(dbRef != null && storageRef !=null && auth != null &&
                !title.isEmpty() && !workerList.isEmpty() && !imagesUri.isEmpty())
        {
            workView.showProgressBar();

            // get download urls of all images
            for (int uploadsImage = 0; uploadsImage < imagesUri.size(); uploadsImage++)
            {

                Uri image = imagesUri.get(uploadsImage);

                final StorageReference imagePath = storageRef.child(image.getLastPathSegment());

                imagePath.putFile(imagesUri.get(uploadsImage))
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot)
                    {
                        // to get download url
                        imagePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>()
                        {
                            @Override
                            public void onSuccess(Uri uri)
                            {
                                counter = counter + 1;
                                // cast uri to string
                                String url = String.valueOf(uri);
                                imageUrls.add(url);

                                // urls completed then upload data
                                if(counter == imagesUri.size())
                                {
                                    // add urls and data to firebase
                                    addDataToFirebase(dbRef , auth, pushKey ,imageUrls ,workerList, title);
                                }

                            }
                        });
                    }
                });

            }

        }
        else
        {
            workView.showMessage("Please images and all fields are require");
        }



    }

    @Override
    public void selectImage()
    {
        workView.onSelectImage();
    }

    @Override
    public void setRecyclerAdapter()
    {
       workView.onSetAdapter();
    }


    // method to add data to firebase database
    private void addDataToFirebase(DatabaseReference mDatabaseReference, FirebaseAuth userAuth,String pushKey,
                                   List<String> urls, List<String> workerList, String title)
    {
        String date = DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());

        Map dataOfComplaint = new HashMap<>();
        dataOfComplaint.put("imageUrl", urls);
        dataOfComplaint.put("date", date);
        dataOfComplaint.put("workers", workerList);
        dataOfComplaint.put("title", title);
        dataOfComplaint.put("uid", userAuth.getCurrentUser().getUid());
        dataOfComplaint.put("pushKey", pushKey);

        mDatabaseReference.child(pushKey)
                .setValue(dataOfComplaint)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()) {

                            workView.hideProgressBar();
                            workView.clearAllFields();
                            workView.showMessage("Successfully uploaded");

                        } else {

                            workView.showMessage("Error in uploading");
                            workView.hideProgressBar();
                        }
                    }
                });
    }




}
