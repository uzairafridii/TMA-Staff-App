package com.example.tmaadminapp.Models;

import com.example.tmaadminapp.Presenters.InfraHomePresenter;
import com.example.tmaadminapp.Views.InfraHomeView;

public class InfraHomePresenterImplementer implements InfraHomePresenter
{
    private InfraHomeView infraHomeView;

    public InfraHomePresenterImplementer(InfraHomeView infraHomeView)
    {
        this.infraHomeView = infraHomeView;
    }

    @Override
    public void clickOnNewsFeedCard() {
        infraHomeView.onNewsFeedClick();
    }

    @Override
    public void clickOnComplaintsCard() {
     infraHomeView.onComplaintsClick();
    }

    @Override
    public void clickOnWorkersListCard() {
     infraHomeView.onWorkerListClick();
    }

    @Override
    public void clickOnBuildingNocCard() {
     infraHomeView.onBuildingNocClick();
    }

    @Override
    public void clickOnComplaintFeedbacksCard() {
        infraHomeView.onComplaintFeedbackClick();
    }

    @Override
    public void clickOnFireFightingCard() {
     infraHomeView.onFireFightingClick();
    }
}
