package com.example.tmaadminapp.Presenters;

import com.google.firebase.database.DatabaseReference;

public interface NewsFeedPresenter
{
    void getAllNewsFeed(DatabaseReference dbRef);
}
