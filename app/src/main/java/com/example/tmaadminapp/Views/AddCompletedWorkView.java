package com.example.tmaadminapp.Views;

public interface AddCompletedWorkView
{
    void showProgressBar();

    void hideProgressBar();

    void showMessage(String message);

    void clearAllFields();

    void onSelectImage();

    void onSetAdapter();
}
