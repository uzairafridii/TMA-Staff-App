package com.example.tmaadminapp.Views;

import com.example.tmaadminapp.AppModules.InfrastructureHead.FireFighting.ModelForFireFighting;

import java.util.List;

public interface FireFightingView
{
    void setAdapter(List<ModelForFireFighting> fireList);

    void setDriverDetails(String name , String phone , String cnic);

    void showProgressBar();

    void hideProgressBar();

    void showMessage(String message);
}
