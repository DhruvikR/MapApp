package com.example.mapapp;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentActivity;

import com.example.mapapp.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.appbar.MaterialToolbar;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,LocationListener {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    List<Address> listGeoCoder;
    private static final int LOCATION_PERMISSION_CODE=101;
    private String address;
    LocationManager locationManager;
    private Toolbar category;
    SupportMapFragment mapFragment;
    SearchView searchView;
    ActionBarDrawerToggle actionBarDrawerToggle;
    DrawerLayout drawerLayout;
    MaterialToolbar materialToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        searchView=findViewById(R.id.sv_location);
        mapFragment=(SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                String location=searchView.getQuery().toString();
                List<Address> addressList=null;
                if(location !=null || !location.equals("")){

                    Geocoder geocoder=new Geocoder(MapsActivity.this);
                    try{
                        addressList = geocoder.getFromLocationName(location,1);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    Address obj = addressList.get(0);
                    LatLng latlng1 = new LatLng(obj.getLatitude(), obj.getLongitude());
                    mMap.addMarker(new MarkerOptions().position(latlng1).title(address));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(latlng1));

                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        SupportMapFragment mapFragment1=(SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment1.getMapAsync(this);

        setUpViews();


    }

    private void setUpViews(){
        setUpDrawerLayout();
    }

    private void setUpDrawerLayout(){
        materialToolbar=findViewById(R.id.appBar);
        ((AppCompatActivity) mapFragment.getActivity()).setSupportActionBar(materialToolbar);
        drawerLayout=findViewById(R.id.mainDrawer);
        actionBarDrawerToggle=new ActionBarDrawerToggle(MapsActivity.this,drawerLayout,R.string.app_name,R.string.app_name);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.navigation_menu, menu);

        // Calling super after populating the menu is necessary here to ensure that the
        // action bar helpers have a chance to handle this event.
        return true;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if(isLocationPermissionGranted())
        {


            // Set Marker in map...................................
//            LatLng sydney = new LatLng(-34, 151);
//            mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

//            try{
//                listGeoCoder=new Geocoder(this).getFromLocationName("BAPS Swaminarayan Chhatralay (APC), " +
//                        "Anand - Vidyanagar Road, Ketivadi, Vallabh Vidyanagar, Anand, Gujarat",1);
//            }
//            catch(Exception e)
//            {
//                e.printStackTrace();
//            }
//            double longitude=listGeoCoder.get(0).getLongitude();
//            double latitude=listGeoCoder.get(0).getLatitude();
            //Print in Log COnsole
//            Log.i("GOOGLE_MAP_TAG","Address h" + "as Longitude ::: "+String.valueOf(longitude)+" And Latitude ::: "+String.valueOf(latitude));

            //Toast String...................................

//            String s = "Address h" + "as Longitude ::: " + String.valueOf(longitude) + " And Latitude ::: " + String.valueOf(latitude);
//            Toast toast=Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT);
//            toast.setMargin(50,50);
//            toast.show();

//            LatLng latlagofAPC=new LatLng(latitude,longitude);
//            mMap.addMarker(new MarkerOptions().position(latlagofAPC).title("APC"));
//            mMap.moveCamera(CameraUpdateFactory.newLatLng(latlagofAPC));
            Geocoder geocoder = new Geocoder(MapsActivity.this, Locale.getDefault());
            mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener()
            {
                @Override
                public void onMapClick(@NonNull LatLng latLng)
                {
                    mMap.clear();
                    try{
                        List<Address> addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
                        Address obj = addresses.get(0);
                        address = obj.getAddressLine(0);
                        String textforToast=address+"\nPlease Click on Marker for Set Notification or Alarm";
                        Toast toast=Toast.makeText(getApplicationContext(),textforToast,Toast.LENGTH_LONG);
                        toast.setMargin(50,50);
                        toast.show();
                        mMap.addMarker(new MarkerOptions().position(latLng).title(address));
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

                    }
                    catch(IOException e){
                        System.out.println(e);
                    }
                }
            });

            mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener()
            {
                @Override
                public boolean onMarkerClick(Marker marker) {
                    // on marker click we are getting the title of our marker
                    // which is clicked and displaying it in a toast message.
                    Intent intent = new Intent(MapsActivity.this, markerClickActivity.class);
                    intent.putExtra("Address_massage", address);
                    startActivity(intent);

                    return false;
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


        getLocation();
    }

    @SuppressLint("MissingPermission")
    private void getLocation()
    {
        try{
            locationManager=(LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,5000,5, (LocationListener) MapsActivity.this);
        }catch (Exception e){
            e.printStackTrace();
        }
    }



    private boolean isLocationPermissionGranted()
    {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)==
        PackageManager.PERMISSION_GRANTED){
            return true;
        }
        else{
            return false;
        }
    }


    private void requestLocationPermissions()
    {
        ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.ACCESS_FINE_LOCATION},
                LOCATION_PERMISSION_CODE);
    }



    @Override
    public void onLocationChanged(@NonNull Location location)
    {
        Toast.makeText(this,""+location.getLatitude()+" "+location.getLongitude(),Toast.LENGTH_LONG);
    }
}