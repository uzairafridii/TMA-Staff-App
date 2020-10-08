package com.example.tmaadminapp.Views;

import com.example.tmaadminapp.AppModules.Regulation.Activities.Bills.BillsModel;

import java.util.List;

public interface BillView
{
    void showMessage(String message);

    void hideLayout();

    void showLayout();

    void getAllConnectionList(List<BillsModel> list);

    void getRefNo(String refNo);

    void selectImage();

}
