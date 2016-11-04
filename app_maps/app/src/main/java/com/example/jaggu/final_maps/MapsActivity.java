package com.example.jaggu.final_maps;

import java.util.ArrayList;

import android.*;
import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, OnMapClickListener, OnMapLongClickListener, OnMarkerClickListener {

    private GoogleMap googleMap;
    private ArrayList<LatLng> arrayPoints = null;
    PolylineOptions polylineOptions;
    private boolean checkClick = false;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    public void onMapReady(GoogleMap map) {
        System.out.println("Before Map Ready");
        googleMap = map;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        googleMap.setMyLocationEnabled(true);
        googleMap.setOnMapClickListener(this);
        googleMap.setOnMapLongClickListener(this);
        googleMap.setOnMarkerClickListener(this);
        System.out.println("After Map Ready");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        arrayPoints = new ArrayList<LatLng>();
        //MapFragment fm = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        //fm.getMapAsync(this); // display zoom map
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapClick(LatLng point) {
        if (checkClick == false) {
            googleMap.addMarker(new MarkerOptions().position(point).icon(BitmapDescriptorFactory.fromResource(R.drawable.pin)));
            arrayPoints.add(point);
        }
    }


    public void countPolygonPoints() {
        if (arrayPoints.size() >= 3) {
            checkClick = true;
            PolygonOptions polygonOptions = new PolygonOptions();
            polygonOptions.addAll(arrayPoints);
            polygonOptions.strokeColor(Color.BLUE);
            polygonOptions.strokeWidth(7);
            polygonOptions.fillColor(Color.CYAN);
            Polygon polygon = googleMap.addPolygon(polygonOptions);
        }
    }

    @Override
    public boolean onMarkerClick(Marker marker) { // TODO Auto-generated method stub
        System.out.println("Marker lat long=" + marker.getPosition());
        System.out.println("First postion check" + arrayPoints.get(0));
        System.out.println("**********All arrayPoints***********" + arrayPoints);
        if (arrayPoints.get(0).equals(marker.getPosition())) {
            System.out.println("********First Point choose************");
            countPolygonPoints();
        }
        return false;
    }


    @Override
    public void onMapLongClick(LatLng latLng) {

    }


}
