package com.example.tmaadminapp.AppModules.ComplaintDetailAndMap;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Toast;

import com.example.tmaadminapp.Models.MapPresenterImplementer;
import com.example.tmaadminapp.Presenters.MapPresenter;
import com.example.tmaadminapp.R;
import com.example.tmaadminapp.Views.MapView;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback , MapView
{
    public static final int REQUEST_CODE = 3;
    private double lat , lng;
    private GoogleMap mMap;
    private MapPresenter mapPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        initViews();

    }

    private void initViews()
    {
        mapPresenter = new MapPresenterImplementer(this, this);
        mapPresenter.initMap();

    }

    // google map callback method
    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        lat = getIntent().getDoubleExtra("lat", 0);
        lng = getIntent().getDoubleExtra("lng", 0);

        if(lat == 0 || lng == 0)
        {
            Toast.makeText(this, "No complaint location added", Toast.LENGTH_SHORT).show();
            findViewById(R.id.directionButton).setVisibility(View.INVISIBLE);
        }
        else
            {

            mMap = googleMap;
           mapPresenter.showMarker(mMap,lat, lng, "Complaint location");
        }


    }

    // button show direction click listener
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void showDirection(View view)
    {
       // mapPresenter.getCurrentLocation();
        mapPresenter.drawDirection(mMap ,lat , lng);

    }

    // check location permission
    @Override
    public boolean onCheckPermission()
    {
        return ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    // request location permission
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermission()
    {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
        }

    }

    // check gps is enable or not
    @Override
    public boolean gpsEnabled() {

        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        boolean locationProvider = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

        if (locationProvider)
        {
            return true;
        }
        else {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this)
                    .setTitle("GPS Enable")
                    .setMessage("GPS is required for to see complaint location .Pleas enable GPS")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                            startActivityForResult(intent, REQUEST_CODE);
                            MapActivity.this.finish();

                        }
                    });
            alertDialog.setCancelable(false);
            alertDialog.show();

        }



        return false;
    }

    // initmap
    @Override
    public void onInitMap()
    {
        SupportMapFragment supportMapFragment  = (SupportMapFragment)
                getSupportFragmentManager().findFragmentById(R.id.map);
        supportMapFragment.getMapAsync(this);
    }

    // check where the permission is granted or not after request
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_CODE && grantResults[0] == PackageManager.PERMISSION_GRANTED)
        {
            Toast.makeText(this, "Please open again to get complaint location", Toast.LENGTH_LONG).show();
        }
    }


    // get current location in onresume method
    @Override
    protected void onResume() {
        super.onResume();
        mapPresenter.getCurrentLocation();
    }
}
