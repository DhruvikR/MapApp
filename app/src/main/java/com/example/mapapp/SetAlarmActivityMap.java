package com.example.mapapp;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

import com.example.mapapp.databinding.ActivitySetAlarmMapBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class SetAlarmActivityMap extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivitySetAlarmMapBinding binding;
    List<Address> listGeoCoder;
    Intent intent;
    String textForAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySetAlarmMapBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        intent=getIntent();



    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        try{
            listGeoCoder=new Geocoder(this).getFromLocationName(intent.getStringExtra("LatLag"),1);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        double longitude=listGeoCoder.get(0).getLongitude();
        double latitude=listGeoCoder.get(0).getLatitude();

        // Add a marker in Sydney and move the camera
        LatLng add = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(add).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(add));
    }


}