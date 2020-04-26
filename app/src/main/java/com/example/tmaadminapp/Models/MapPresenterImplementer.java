package com.example.tmaadminapp.Models;

import com.example.tmaadminapp.Presenters.MapPresenter;
import com.example.tmaadminapp.Views.MapView;
import com.google.android.gms.location.FusedLocationProviderClient;

public class MapPresenterImplementer implements MapPresenter
{

    private MapView mapView;

    public MapPresenterImplementer(MapView mapView) {
        this.mapView = mapView;
    }

    @Override
    public void initMap() {

        if(mapView.gpsEnabled())
        {
              if(mapView.onCheckPermission())
              {
                   mapView.onInitMap();
              }
              else
              {
                  mapView.onRequestPermission();
              }
        }



    }

    @Override
    public void getCurrentLocation()
    {
        mapView.onGetCurrentLocation();
    }
}
