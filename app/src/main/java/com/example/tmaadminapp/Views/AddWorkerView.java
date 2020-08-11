package com.example.tmaadminapp.Views;

import com.example.tmaadminapp.AppModules.WorkersListAndDetails.Models.ModelForWorkerList;

import java.util.List;

public interface AddWorkerView
{
    void showProgressBar();

    void hideProgressBar();

    void showMessage(String message);

    void getWorkersList(List<ModelForWorkerList> workerList);

    void hideLayout();

    void showLayout();

}
