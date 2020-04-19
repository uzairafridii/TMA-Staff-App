package com.example.tmaadminapp.Presenters;

import com.google.firebase.database.DatabaseReference;

public interface AdminNewsPresenter
{
    //firebase methods
    void addNews(DatabaseReference dbAddNewsReference , String title , String description , String author , String date);
    void getAllNews(DatabaseReference dbGetAllReference);

    void showNewsPostingDialog();

}
