package com.example.mapapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.example.mapapp.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    List<Address> listGeoCoder;
    private static final int LOCATION_PERMISSION_CODE=101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if(isLocationPermissionGranted()){


            // Set Marker in map...................................
//            LatLng sydney = new LatLng(-34, 151);
//            mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

            try{
                listGeoCoder=new Geocoder(this).getFromLocationName("BAPS Swaminarayan Chhatralay (APC), Anand - Vidyanagar Road, Ketivadi, Vallabh Vidyanagar, Anand, Gujarat",1);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            double longitude=listGeoCoder.get(0).getLongitude();
            double latitude=listGeoCoder.get(0).getLatitude();
            //Print in Log COnsole
//            Log.i("GOOGLE_MAP_TAG","Address h" + "as Longitude ::: "+String.valueOf(longitude)+" And Latitude ::: "+String.valueOf(latitude));

            //Toast String...................................

//            String s = "Address h" + "as Longitude ::: " + String.valueOf(longitude) + " And Latitude ::: " + String.valueOf(latitude);
//            Toast toast=Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT);
//            toast.setMargin(50,50);
//            toast.show();

            LatLng latlagofAPC=new LatLng(latitude,longitude);
            mMap.addMarker(new MarkerOptions().position(latlagofAPC).title("APC"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latlagofAPC));

            mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                @Override
                public void onMapClick(@NonNull LatLng latLng) {
                    mMap.addMarker(new MarkerOptions().position(latLng).title("Click Here"));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

                    String s = "Address h" + "as Longitude ::: " + String.valueOf(latLng.longitude) + " And Latitude ::: " + String.valueOf(latLng.latitude);
                    Toast toast=Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT);
                    toast.setMargin(50,50);
                    toast.show();
                }
            });


            if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)==
                    PackageManager.PERMISSION_GRANTED){
                mMap.setMyLocationEnabled(true);
            }



        }
        else{
            requestLocationPermissions();
        }
//
//        Geocoder geocoder=new Geocoder(this);
//        geocoder.getFromLocation()



    }



    private boolean isLocationPermissionGranted(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)==
        PackageManager.PERMISSION_GRANTED){
            return true;
        }
        else{
            return false;
        }
    }

    private void requestLocationPermissions(){
        ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.ACCESS_FINE_LOCATION},LOCATION_PERMISSION_CODE);
    }

}