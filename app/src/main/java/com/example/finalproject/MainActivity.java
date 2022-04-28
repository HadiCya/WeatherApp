package com.example.finalproject;

import static com.google.android.gms.location.LocationRequest.PRIORITY_HIGH_ACCURACY;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationRequest;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.function.Consumer;


//import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.io.Charsets;



public class MainActivity extends AppCompatActivity {

    public static MyTask myTask;
    public static String locate;
    public LocationListener stinky;

    public TextView location;
    public TextView update;
    public TextView status ;
    public TextView temp ;
    public TextView precipitation;
    public TextView cloudcast ;
    public TextView feelslike;
    public TextView windsp ;
    public TextView pressuresp;
    public TextView humiditysp;
    private FusedLocationProviderClient fusedLocationClient;
    BottomNavigationView navigation;

    @SuppressLint({"MissingPermission", "NewApi"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_main);

        Log.d("ME", "PRECALL ****************");


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.ACCESS_FINE_LOCATION }, 1);
        }

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        fusedLocationClient.getCurrentLocation(PRIORITY_HIGH_ACCURACY, null).addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null && locate == null) {
                    Geocoder geocoder;
                    List<Address> addresses;
                    geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
                    try {
                        addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                        locate = addresses.get(0).getPostalCode();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });


        navigation = findViewById(R.id.bottom_navigation);
        navigation.setSelectedItemId(R.id.weather);
        navigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();
                switch (id){
                    case R.id.weather:
                        return true;

                    case R.id.locate:
                        startActivity(new Intent(getApplicationContext(), LocationScreen.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.alarm:
                        startActivity(new Intent(getApplicationContext(), AlarmScreen.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return true;
            }
        });




        location = findViewById(R.id.location);
        update = findViewById(R.id.update);
        status = findViewById(R.id.status);
        temp = findViewById(R.id.temp);
        feelslike = findViewById(R.id.feelslike);
        precipitation = findViewById(R.id.precipitation);
        cloudcast = findViewById(R.id.cloudcast);
        windsp = findViewById(R.id.wind);
        pressuresp = findViewById(R.id.pressure);
        humiditysp = findViewById(R.id.humidity);

        myTask = new MyTask(this);
        myTask.execute();

        Log.d("ME", "API CALLED UPON****************");
        System.out.println(locate);




    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(MainActivity.this, "Location Permission Granted", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(MainActivity.this, "Location Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}







