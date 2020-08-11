package com.example.tmaadminapp.Views;

import com.example.tmaadminapp.AppModules.InfrastructureHead.BuildingNoc.ModelForNoc;

import java.util.List;

public interface NocView {
    void onSetNocRecyclerAdapter(List<ModelForNoc> nocList);

    void hideNoItemFoundLayout();

    void showNoItemFoundLayout();
}
