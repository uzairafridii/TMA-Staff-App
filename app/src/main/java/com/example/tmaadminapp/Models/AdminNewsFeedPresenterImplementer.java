package com.example.tmaadminapp.Models;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.tmaadminapp.AppModules.NewsFeed.ModelForNewsFeed.NewsFeedModel;
import com.example.tmaadminapp.Presenters.AdminNewsPresenter;
import com.example.tmaadminapp.Views.AdminNewsView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminNewsFeedPresenterImplementer implements AdminNewsPresenter
{
    private AdminNewsView newsFeedView;
    private List<NewsFeedModel> list = new ArrayList<>();

    public AdminNewsFeedPresenterImplementer(AdminNewsView newsFeedView) {
        this.newsFeedView = newsFeedView;
    }

    @Override
    public void addNews(DatabaseReference dbAddNewsReference, String title, String description, String author, String date)
    {
        if(!title.isEmpty() && !description.isEmpty() && !author.isEmpty() && !date.isEmpty()
                && dbAddNewsReference != null)
        {
            newsFeedView.onShowProgressBar();

            DatabaseReference ref = dbAddNewsReference.push();
            String pushKey = ref.getRef().getKey();

            Map<String , String> postData = new HashMap<>();
            postData.put("title",title);
            postData.put("description", description);
            postData.put("author", author);
            postData.put("dateAndTime", date);
            postData.put("pushKey", pushKey);

            ref.setValue(postData)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if(task.isSuccessful())
                            {

                                newsFeedView.onShowMessage("Successfully posted");
                                newsFeedView.onHideProgressBar();

                            }
                            else
                            {
                                newsFeedView.onHideProgressBar();
                                newsFeedView.onShowMessage("error in posting news");

                            }

                        }
                    });;

        }
        else
        {
            newsFeedView.onShowMessage("Please fill the fileds");
        }

    }


    @Override
    public void getAllNews(DatabaseReference dbGetAllReference)
    {
        if(dbGetAllReference == null) {
            newsFeedView.onShowMessage("no reference found");
        }
        else {
            dbGetAllReference.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    // get data from firebase
                    NewsFeedModel newsFeedData = dataSnapshot.getValue(NewsFeedModel.class);
                    list.add(newsFeedData);
                    newsFeedView.onGetAllNews(list);



                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });



        }

    }


    @Override
    public void showNewsPostingDialog()
    {
        newsFeedView.showPostinNewsForm();
    }


}
