package com.example.tmaadminapp.Models;

import com.example.tmaadminapp.Presenters.AdminHomePresenter;
import com.example.tmaadminapp.Views.AdminHomeView;

public class AdminHomePresenterImplementer
        implements AdminHomePresenter
{

    private AdminHomeView homeView;

    public AdminHomePresenterImplementer(AdminHomeView homeView) {
        this.homeView = homeView;
    }

    @Override
    public void newsFeedCardClick() {

        homeView.onNewsFeedCardClick();
    }

    @Override
    public void staffManagementCardClick() {

        homeView.onStaffManagementCardClick();
    }

    @Override
    public void complaintsCardClick() {

        homeView.onComplaintsCardClick();
    }

    @Override
    public void financeCardClick() {

        homeView.onFinanceCardClick();
    }

}
