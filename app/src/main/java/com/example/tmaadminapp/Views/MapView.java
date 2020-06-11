package com.example.tmaadminapp.Views;

public interface MapView
{

    boolean onCheckPermission();

    void onRequestPermission();

    boolean gpsEnabled();

    void onInitMap();

}
