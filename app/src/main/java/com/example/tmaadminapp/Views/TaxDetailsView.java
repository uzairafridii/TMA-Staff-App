package com.example.tmaadminapp.Views;

import com.example.tmaadminapp.AppModules.Regulation.Activities.Tax.TaxModel;

import java.util.List;

public interface TaxDetailsView
{

    void selectImage();
    void message(String msg);

    void hideLayout();
    void showLayout();

    void showList(List<TaxModel> taxModelList);


}
