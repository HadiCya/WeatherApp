package com.example.finalproject;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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
import java.util.Locale;


//import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.io.Charsets;

public class MainActivity extends AppCompatActivity {

    String locate = "48128";

    public TextView location;
    public TextView update;
    public TextView status ;
    public TextView temp ;
    public TextView precipitation;
    public TextView cloudcast ;
    public TextView feelslike;
    public TextView windsp ;
    public TextView pressuresp;
    public TextView humiditysp ;

    String address = " ";
    String updatedat = " ";
    String weatherdescript = " ";
    String tempmain = " ";
    String precip = " ";
    String cc = " ";
    String fl = " ";
    String windSpeed = " ";
    String pressure = " ";
    String humidity = " ";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_main);

       // String response = ("https://api.openweather.org/data/2.5/weather?q=" + city + "&units=metric&appid=" + apicode);

        Log.d("ME", "PRECALL ****************");

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

        MyTask myTask = new MyTask(this, address, updatedat, weatherdescript, tempmain, precip, cc,
                fl, windSpeed, pressure, humidity, locate);
        myTask.execute();
        String[] fnllist = myTask.fnllist;
        System.out.println("AYO " + fnllist[0]);




        Log.d("ME", "API CALLED UPON****************");



        location.setText(address);
        update.setText(updatedat);
        status.setText(weatherdescript);
        temp.setText(tempmain);
        feelslike.setText(fl);
        precipitation.setText(precip);
        cloudcast.setText(cc);
        windsp.setText(windSpeed);
        pressuresp.setText(pressure);
        humiditysp.setText(humidity);


    }
}







