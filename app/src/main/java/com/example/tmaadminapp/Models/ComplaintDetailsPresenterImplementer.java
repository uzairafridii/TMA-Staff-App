package com.example.tmaadminapp.Models;

import android.content.Context;
import android.content.Intent;

import com.example.tmaadminapp.Presenters.ComplaintDetailsPresenter;
import com.example.tmaadminapp.Views.ComplaintDetailsView;
import com.example.tmaadminapp.Views.ComplaintsView;

public class ComplaintDetailsPresenterImplementer implements ComplaintDetailsPresenter
{
   private ComplaintDetailsView view;

    public ComplaintDetailsPresenterImplementer(ComplaintDetailsView view) {
        this.view = view;
    }

    @Override
    public void setImagesAdapter() {
         view.onSetAdapter();
    }

    @Override
    public void setTitleAndDesc() {
        view.setTitleAndDescription();
    }
}
