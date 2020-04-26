package com.example.tmaadminapp.AppModules.Complaint_CompletedWork_Map_Details;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.tmaadminapp.Models.MapPresenterImplementer;
import com.example.tmaadminapp.Presenters.MapPresenter;
import com.example.tmaadminapp.R;
import com.example.tmaadminapp.Views.MapView;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import static com.google.android.gms.maps.model.BitmapDescriptorFactory.defaultMarker;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback , MapView
{
    public static final int REQUEST_CODE = 3;
    private double lat , lng , currentLat , currentLng;
    private GoogleMap mMap;
    private FusedLocationProviderClient mLocationService;
    private MapPresenter mapPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        initViews();

    }

    private void initViews()
    {
        mapPresenter = new MapPresenterImplementer(this);
        mLocationService = new FusedLocationProviderClient(this);

        mapPresenter.initMap();

    }

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
            showMarker(lat, lng, "Complaint location");
        }


    }

    private void showMarker(double lat , double lng , String title)
    {
        LatLng latLng = new LatLng(lat, lng);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 15);
        mMap.animateCamera(cameraUpdate);
        MarkerOptions markerOptions = new MarkerOptions()
                .title(title)
                .icon(defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                .position(latLng);

        if(title.equals("Complaint location"))
        {
            markerOptions.icon(defaultMarker(BitmapDescriptorFactory.HUE_RED));
        }


        mMap.addMarker(markerOptions);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void showDirection(View view)
    {
        mapPresenter.getCurrentLocation();
        findViewById(R.id.directionButton).setVisibility(View.INVISIBLE);

    }

    @Override
    public boolean onCheckPermission()
    {
        return ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

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
                    .setMessage("GPS is required for this app to work .Pleas enable GPS")
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

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onGetCurrentLocation()
    {
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED  &&
                checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED)
        {
            return;
        }


        mLocationService.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onComplete(@NonNull Task<Location> task) {

                if (task.getResult() != null)
                {
                    Location location = task.getResult();
                    currentLat = location.getLatitude();
                    currentLng = location.getLongitude();

                    drawPolyLine(currentLat , currentLng);
                }
                else
                {
                    Toast.makeText(MapActivity.this, "No current location found", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void onInitMap()
    {
        SupportMapFragment supportMapFragment  = (SupportMapFragment)
                getSupportFragmentManager().findFragmentById(R.id.map);
        supportMapFragment.getMapAsync(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void drawPolyLine(double currentLat , double currentLng)
    {
        showMarker(currentLat , currentLng , "Your current location");

        PolylineOptions polylineOptions = new PolylineOptions();
        polylineOptions.add(new LatLng(lat , lng) , new LatLng(currentLat , currentLng));
        polylineOptions.color(getColor(R.color.sign_in_txt_color));
        mMap.addPolyline(polylineOptions);
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

}
