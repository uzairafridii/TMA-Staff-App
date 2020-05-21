package com.example.tmaadminapp.Views;

import com.example.tmaadminapp.AppModules.UnionCouncilHead.CertificatesModel;

import java.util.List;

public interface CertificatesView
{
    void getCertificates(List<CertificatesModel> list);

    void hideTextView();

    void showMessage(String message);
}
