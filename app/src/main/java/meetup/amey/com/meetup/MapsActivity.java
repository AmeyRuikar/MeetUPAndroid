package meetup.amey.com.meetup;

import java.util.ArrayList;

import android.*;
import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
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
import com.google.android.gms.maps.model.*;

import android.location.*;
import android.util.Log;
import android.view.View;
import android.widget.*;
import android.app.Fragment;
import android.support.v4.app.FragmentManager;

import io.github.yavski.fabspeeddial.FabSpeedDial;
import io.github.yavski.fabspeeddial.SimpleMenuListenerAdapter;

import android.view.MenuItem;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, OnMapClickListener, OnMapLongClickListener, OnMarkerClickListener {

    private GoogleMap googleMap;
    private GoogleMap googleMapCopy;
    private ArrayList<LatLng> arrayPoints = null;
    private ArrayList<eventMarkerObject> returnedEvents = new ArrayList<eventMarkerObject>();
    PolylineOptions polylineOptions;
    private boolean checkClick = false;
    Button seeEvents;
    String eventid;
    int flag = 0;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    public void onMapReady(GoogleMap map) {

        System.out.println("Before Map Ready");
        googleMap = map;
        googleMapCopy = map;
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

        LocationManager locationManager = (LocationManager) getSystemService(getApplicationContext().LOCATION_SERVICE);
        Criteria criteria = new Criteria();

        Location location = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));
        if (location != null) {
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 13));

            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(new LatLng(location.getLatitude(), location.getLongitude()))      // Sets the center of the map to location user
                    .zoom(17)                   // Sets the zoom
                    .bearing(0)                // Sets the orientation of the camera to east
                    .tilt(45)                   // Sets the tilt of the camera to 30 degrees
                    .build();                   // Creates a CameraPosition from the builder
            map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        }


        FabSpeedDial fabSpeedDial = (FabSpeedDial) findViewById(R.id.mapOptions);
        fabSpeedDial.setMenuListener(new SimpleMenuListenerAdapter() {
            @Override
            public boolean onMenuItemSelected(MenuItem menuItem) {

                Log.i("fab", menuItem.toString());

                if (menuItem.getTitle().equals("List View")) {

                    globals.eventArrayForList = returnedEvents;

                    Intent intent = new Intent(getApplicationContext(), showEventList.class);
                    startActivity(intent);
                } else if (menuItem.getTitle().equals("Clear All")) {

                    //TODO: clear all the points/map



                }

                return false;
            }
        });

        seeEvents = (Button) findViewById(R.id.btn_show);
        seeEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seeEvents.setVisibility(View.INVISIBLE);


                new loadEventMarkers(arrayPoints, googleMap, returnedEvents).execute();
                flag =1;




                ImageView shareImageView = (ImageView) findViewById(R.id.shareImageView_maps);

                shareImageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        //Toast.makeText(getApplicationContext(), "now sharing the event with others", Toast.LENGTH_SHORT).show();

                        AlertDialog.Builder builder = new AlertDialog.Builder(MapsActivity.this);
                        TextView n = (TextView) findViewById(R.id.nameEventMaps);

                        builder.setTitle("Create this Event at " + n.getText().toString());
                        builder.setMessage("Share with others?");
                        eventid = "";

                        for(int i = 0; i < returnedEvents.size(); i++){
                            if(returnedEvents.get(i).getEventName().equals(n.getText().toString())){
                                eventid = returnedEvents.get(i).getEventid();
                                break;
                            }
                        }


                        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                //Toast.makeText(getActivity(), titleTextView.getText() + " just make the fragemnt now", Toast.LENGTH_SHORT).show();

                                //later move to async task on post execute

                                new AsyncCreateEvent(getApplicationContext()).execute(eventid);
                                dialog.dismiss();

                                /*
                                Intent i = new Intent(MapsActivity.this, fragment.class);
                                // set the new task and clear flags
                                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                                startActivity(i);

                                */

                            }
                        });


                        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.dismiss();

                            }
                        });

                        AlertDialog alert = builder.create();
                        alert.show();


                    }
                });






            }
        });

        RelativeLayout banner = (RelativeLayout) findViewById(R.id.activity_main_relative);
        banner.setVisibility(View.INVISIBLE);

        /*
        FloatingActionButton listVIew = (FloatingActionButton) findViewById(R.id.list_view);

        listVIew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(getApplicationContext(), cardsFragment.class);
                //startActivity(intent);

                Intent intent = new Intent(getApplicationContext(), showEventList.class);
                startActivity(intent);
            }
        });
        */





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
            googleMap.addMarker(new MarkerOptions().position(point).icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_marker)));
            arrayPoints.add(point);
        }
    }


    public void countPolygonPoints() {
        if (arrayPoints.size() >= 3) {
            checkClick = true;
            PolygonOptions polygonOptions = new PolygonOptions();
            polygonOptions.addAll(arrayPoints);
            polygonOptions.strokeColor(Color.GRAY);
            polygonOptions.strokeWidth(7);
            polygonOptions.fillColor(Color.argb(80, 100, 100, 100));
            Polygon polygon = googleMap.addPolygon(polygonOptions);


            seeEvents = (Button) findViewById(R.id.btn_show);
            seeEvents.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean onMarkerClick(Marker marker) { // TODO Auto-generated method stub
        System.out.println("Marker lat long=" + marker.getPosition());
        System.out.println("First postion check" + arrayPoints.get(0));
        System.out.println("**********All arrayPoints***********" + arrayPoints);
        if (arrayPoints.get(0).equals(marker.getPosition())) {
            System.out.println("********First Point choose************");
            arrayPoints.add(arrayPoints.get(0));
            countPolygonPoints();
        }



        if(flag ==1){

            for(int i=0; i< returnedEvents.size(); i++){

                if(returnedEvents.get(i).getPosition().equals(marker.getPosition())){

                    RelativeLayout banner = (RelativeLayout) findViewById(R.id.activity_main_relative);
                    banner.setVisibility(View.VISIBLE);

                    TextView n = (TextView) findViewById(R.id.nameEventMaps);
                    n.setText(returnedEvents.get(i).getEventName());

                    TextView n1 = (TextView) findViewById(R.id.genreMap);
                    if(returnedEvents.get(i).getSubgenre().equals("") || returnedEvents.get(i).getSubgenre().equals("Undefined")){
                        n1.setText(returnedEvents.get(i).getGenre());
                    }
                    else{
                        n1.setText(returnedEvents.get(i).getGenre() +" - "+returnedEvents.get(i).getSubgenre());
                    }

                    TextView n2 = (TextView) findViewById(R.id.ratingsMap);
                    if(!returnedEvents.get(i).getRating().equals("0")){
                        n2.setText("Rating: "+returnedEvents.get(i).getRating()+"/5");
                    }




                }

            }

        }




        return false;
    }


    @Override
    public void onMapLongClick(LatLng latLng) {

    }


}