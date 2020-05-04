package com.example.tmaadminapp.Models;

import com.example.tmaadminapp.Presenters.SanitationMainPresenter;
import com.example.tmaadminapp.Views.SanitationMainView;

public class SanitationMainPresenterImplementer implements SanitationMainPresenter
{

    private SanitationMainView sanitationMainView;

    public SanitationMainPresenterImplementer(SanitationMainView sanitationMainView) {
        this.sanitationMainView = sanitationMainView;
    }

    @Override
    public void newsFeed()
    {
      sanitationMainView.onNewsFeedCardClick();
    }

    @Override
    public void complaints() {
        sanitationMainView.onComplaintCardClick();
    }

    @Override
    public void workersList() {
        sanitationMainView.onWorkerListCardClick();
    }

    @Override
    public void feedback()
    {
      sanitationMainView.onFeedBackCardClick();
    }


}
