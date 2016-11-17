package com.example.mahe.voyage;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    public double SRC_LONG =0.0;
    public double SRC_LANG =0.0;
    public double DEST_LONG =0.0;
    public double DEST_LANG =0.0;
    MyDBHelper helper;
    Main6Activity main6Activity;
    public LatLng LatLangsource,LatLngdestination; // = new LatLng("","");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps1);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        Intent intent = getIntent();
       String destination=  intent.getStringExtra("destination");
        String source =  intent.getStringExtra("source");

        source = helper.getPlaceCoordinates(source);
        destination = helper.getPlaceCoordinates(destination);

        String[] palceCoordinates = destination.split(",");
        long destLat = Long.parseLong(palceCoordinates[0]);
        long destLng = Long.parseLong(palceCoordinates[1]);
        LatLngdestination = new LatLng(destLat,destLng);

        palceCoordinates = source.split(",");
        long sourceLat = Long.parseLong(palceCoordinates[0]);
        long sourceLng = Long.parseLong(palceCoordinates[1]);
        LatLangsource = new LatLng(sourceLat,sourceLng);

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    public void processMaps(){}
}
