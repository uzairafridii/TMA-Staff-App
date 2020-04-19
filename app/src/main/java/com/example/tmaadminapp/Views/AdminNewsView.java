package com.example.tmaadminapp.Views;

import com.example.tmaadminapp.AppModules.NewsFeed.ModelForNewsFeed.NewsFeedModel;

import java.util.List;

public interface AdminNewsView
{

    // firebase callbacks
    void onShowProgressBar();
    void onHideProgressBar();
    void onShowMessage(String message);
    // post news callback
    void showPostinNewsForm();

    void onGetAllNews(List<NewsFeedModel> newsFeedModelList);

}
