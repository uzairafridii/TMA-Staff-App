package com.example.tmaadminapp.Views;

import com.example.tmaadminapp.AppModules.NewsFeed.ModelForNewsFeed.NewsFeedModel;

import java.util.List;

public interface NewsFeedView {
    void onSetNewsRecyclerAdapter(List<NewsFeedModel> list);

    void hideLayout();

    void showLayout();
}
