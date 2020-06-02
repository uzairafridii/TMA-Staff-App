package com.example.tmaadminapp.Views;

import java.util.List;

public interface AddCompletedWorkView
{
    void showProgressBar();

    void hideProgressBar();

    void showMessage(String message);

    void clearAllFields();

    void onSelectImage();

    void onSetAdapter();

    void onSetWorkerListSpinnerAdapter(List<String> workerList);
}
