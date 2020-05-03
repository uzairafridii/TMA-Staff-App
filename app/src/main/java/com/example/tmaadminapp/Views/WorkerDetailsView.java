package com.example.tmaadminapp.Views;

import com.example.tmaadminapp.AppModules.WorkersListAndDetails.ModelForWorkersRating;

import java.util.List;

public interface WorkerDetailsView
{
    void onGetAllRatings(List<ModelForWorkersRating> ratingList);

}
