package com.example.akovosos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;


public class MainActivity2 extends AppCompatActivity implements OnMapReadyCallback {


    private final int FINE_PERMISSION_CODE = 1;
    private GoogleMap MyMap;
    Location currentLocation;
    FusedLocationProviderClient fusedLocationProviderClient;
    private int RequestCode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        getLastLocation();


        Button button = findViewById(R.id.ypmeg);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);
            }

        });

        //setContentView(R.layout.activity_main2);
        Button button1 = findViewById(R.id.ypkal);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
                startActivity(intent);
            }
        });


    }

    private void getLastLocation() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, FINE_PERMISSION_CODE);

            return;
        }


        Task<Location> task = fusedLocationProviderClient.getLastLocation();
         task.addOnSuccessListener(new OnSuccessListener<Location>(){
        @Override
         public void onSuccess(Location location){
        if (location != null) {
         currentLocation = location;

         SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(MainActivity2.this);
          }
          }

        });

    }


        public void OnMapReady(@NonNull GoogleMap googleMap) {


        }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(RequestCode == FINE_PERMISSION_CODE) {
           if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                //getLastLocation();

            }else{
                Toast.makeText(this,"location permission is denied",Toast.LENGTH_SHORT).show();
           }
        }
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        MyMap = googleMap;

        LatLng sydney = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
        MyMap.addMarker(new MarkerOptions().position(sydney).title("My Location"));
        MyMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

    }
    }














