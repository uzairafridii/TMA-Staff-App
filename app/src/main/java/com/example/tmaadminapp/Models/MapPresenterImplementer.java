package com.example.tmaadminapp.Models;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.location.Location;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tmaadminapp.AppModules.ComplaintDetailAndMap.MapActivity;
import com.example.tmaadminapp.Presenters.MapPresenter;
import com.example.tmaadminapp.R;
import com.example.tmaadminapp.Views.MapView;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static com.google.android.gms.maps.model.BitmapDescriptorFactory.defaultMarker;

public class MapPresenterImplementer implements MapPresenter {

    private MapView mapView;
    private Context context;
    private double currentLat, currentLng;


    public MapPresenterImplementer(MapView mapView, Context context) {
        this.mapView = mapView;
        this.context = context;
    }

    @Override
    public void initMap() {

        if (mapView.gpsEnabled()) {
            if (mapView.onCheckPermission()) {
                mapView.onInitMap();

            } else {
                mapView.onRequestPermission();
            }
        }


    }

    @Override
    public void getCurrentLocation() {

        if (mapView.onCheckPermission()) {
            getLastLocation();
        } else {
            mapView.onRequestPermission();
        }
    }

    @Override
    public void showMarker(GoogleMap mMap, double lat, double lng, String title) {

        // pass lat and lng to latlng constuctor
            LatLng latLng = new LatLng(lat, lng);
            // call show marker on map method
            showMakerOnMap(latLng , mMap , title);

    }

    @Override
    public void drawDirection(final GoogleMap map , final double toLatitude , final double toLongitude)
    {
        //Getting the URL
        String url = makeURL(currentLat, currentLng, toLatitude, toLongitude);

        //Showing a dialog till we get the route
        final ProgressDialog loading = ProgressDialog.show(context,"Getting Route", "Please wait...", false, false);

        //Creating a string request
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loading.dismiss();
                //Calling the method drawPath to draw the path
                drawPath(response , map , toLatitude , toLongitude);
            }
        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.dismiss();
                    }
                });

        //Adding the request to request queue
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);

    }

    // show markers on map from  start point to end point
    private void showMakerOnMap(LatLng latLng, GoogleMap mMap , String title)
    {
        // update the camera to the latlng pass to LatLng class constructor
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 16);
        mMap.animateCamera(cameraUpdate);
        MarkerOptions markerOptions = new MarkerOptions()
                .title(title)
                .icon(defaultMarker(BitmapDescriptorFactory.HUE_RED))
                .position(latLng);
        mMap.addMarker(markerOptions);
    }

    // get the user current location
    private void getLastLocation() {

        // get the current user last location
        FusedLocationProviderClient mLocationService = new FusedLocationProviderClient(context);
        mLocationService.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onComplete(@NonNull Task<Location> task) {

                if (task.getResult() != null) {
                    Location location = task.getResult();
                    currentLat = location.getLatitude();
                    currentLng = location.getLongitude();

                } else {
                    Toast.makeText(context, "No current location found", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    // make url for request to the direction between two points
    public String makeURL(double sourcelat, double sourcelog, double destlat, double destlog) {
        StringBuilder urlString = new StringBuilder();
        urlString.append("https://maps.googleapis.com/maps/api/directions/json");
        urlString.append("?origin=");// from
        urlString.append(Double.toString(sourcelat));
        urlString.append(",");
        urlString
                .append(Double.toString(sourcelog));
        urlString.append("&destination=");// to
        urlString
                .append(Double.toString(destlat));
        urlString.append(",");
        urlString.append(Double.toString(destlog));
        urlString.append("&sensor=false&mode=driving&alternatives=true");
        urlString.append("&key=AIzaSyC25fz7R_AYrRD5v6spK89aW9yt2Oiafl4");
        return urlString.toString();
    }

    // parse the json object and get the points between two locations
    public void drawPath(String result , GoogleMap mMap , double toLat , double tolng) {

        // show marker on destination and on source points
        showMakerOnMap(new LatLng(toLat, tolng),mMap, "Complaint Location");
        showMakerOnMap(new LatLng(currentLat, currentLng), mMap , "Your Current Location");


        try {
            //Parsing json
            final JSONObject json = new JSONObject(result);
            JSONArray routeArray = json.getJSONArray("routes");
            JSONObject routes = routeArray.getJSONObject(0);
            JSONObject overviewPolylines = routes.getJSONObject("overview_polyline");
            String encodedString = overviewPolylines.getString("points");
            List<LatLng> list = decodePoly(encodedString);
            Polyline line = mMap.addPolyline(new PolylineOptions()
                    .addAll(list)
                    .width(7)
                    .color(Color.BLUE)
                    .geodesic(true)
            );


        } catch (JSONException e) {

        }
    }

    // decode the points of locations
    private List<LatLng> decodePoly(String encoded) {

        List<LatLng> poly = new ArrayList<LatLng>();
        int index = 0, len = encoded.length();
        int lat = 0, lng = 0;

        while (index < len) {
            int b, shift = 0, result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;

            shift = 0;
            result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;

            LatLng p = new LatLng((((double) lat / 1E5)),
                    (((double) lng / 1E5)));
            poly.add(p);
        }

        return poly;
    }

}
