package com.example.tmaadminapp.Presenters;


import com.google.android.gms.maps.GoogleMap;

public interface MapPresenter
{

    void initMap();

    void getCurrentLocation();

    void showMarker(GoogleMap mMap, double lat, double lng, String title);

    void drawDirection(GoogleMap mMap, double toLat , double toLng);

}
