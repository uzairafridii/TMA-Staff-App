package com.example.tmaadminapp.Views;

import android.location.Location;

public interface MapView
{

    boolean onCheckPermission();

    void onRequestPermission();

    boolean gpsEnabled();

    void onGetCurrentLocation();

    void onInitMap();

}
