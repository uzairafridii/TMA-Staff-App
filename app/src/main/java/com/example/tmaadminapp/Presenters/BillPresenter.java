package com.example.tmaadminapp.Presenters;

import android.net.Uri;

public interface BillPresenter
{
    void addNewConnection();

    void getAllUserConnections();

    void addCitizenBill(String refNo);

    void getUriOfImage(Uri uri);
}
