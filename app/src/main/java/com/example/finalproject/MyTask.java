package com.example.finalproject;

import android.Manifest;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Looper;
import android.util.Log;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Locale;

public class MyTask extends AsyncTask<Void,Void,String> {
    public MainActivity mainActivity;
    public String address, updatedat, weatherdescript, tempmain, precip, cc, fl, windSpeed, pressure, humidity;


    String urlStr;

    public MyTask(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    protected String doInBackground(Void... params) {


        while(mainActivity.locate == null){

        }


        String jsonStr = "";
        urlStr = ("https://api.weatherapi.com/v1/current.json?key=0db3e6d81c4f426db8a175734222604&q="+ mainActivity.locate +"&aqi=no");
        URL myUrl = null;
        try {
            myUrl = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection)
                    myUrl.openConnection();
            connection.setRequestMethod("GET");
            BufferedReader in;
            in = new BufferedReader(new
                    InputStreamReader(connection.getInputStream()));
            StringBuffer sb = new StringBuffer("");
            String line = "";
            while ((line = (String) in.readLine()) != null) {
                sb.append(line);
            }
            in.close();
            jsonStr = sb.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.d("ME", jsonStr + "********************");

        return jsonStr;
    }


    @Override
    protected void onPostExecute(String jsonStr) {

        JSONObject jsonObj = new JSONObject();


        try {
            jsonObj = new JSONObject(jsonStr);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("ME", "****************" + String.valueOf(jsonObj) + "***************");

        JSONObject locationn = null;
        try {
            locationn = jsonObj.getJSONObject("location");
        } catch (JSONException e) {
            e.printStackTrace();
        }



        JSONObject current = null;
        try {
            current = jsonObj.getJSONObject("current");
        } catch (JSONException e) {
            e.printStackTrace();
        }


       // Log.d("ME", jsonStr + "**********************");
       // Log.d("ME", name + "**********************");

        JSONObject condition = null;
        try {
            condition = current.getJSONObject("condition");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String name = null;

        try {
            name = locationn.getString("name");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String region = null;

        try {
            region = locationn.getString("region");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ////////////////////////////////
        address = name + ", " + region;
        ////////////////////////////////

        String updat = null;

        try {
            updat = current.getString("last_updated");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ////////////////////////////////
        updatedat = "Last updated: " + updat;
        ////////////////////////////////



        String desc = null;

        try {
            desc = condition.getString("text");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ////////////////////////////////
        weatherdescript = "Todays Forecast: " + desc;
        ////////////////////////////////


        double tempf = 0;

        try {
            tempf = (double)current.getLong("temp_f");
        } catch (JSONException e) {
            e.printStackTrace();
        }


        ////////////////////////////////
        tempmain = tempf + "°F";
        ////////////////////////////////


        double feel = 0;

        try {
            feel = (double)current.getLong("feelslike_f");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ////////////////////////////////
        fl = "Feels like " + feel + "°F";
        ////////////////////////////////

        double cperc = 0;

        try {
            cperc = (double)current.getLong("cloud");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ////////////////////////////////
        cc = "Cloud Cast: " + cperc + "%";
        ////////////////////////////////

        double windval = 0;
        try {
            windval = (double)current.getLong("wind_mph");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String winddir = null;

        try {
            winddir = current.getString("wind_dir");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ////////////////////////////////
        windSpeed = "Wind: " + windval + "mph ->" + winddir;
        ////////////////////////////////

        double presnum = 0;
        try {
            presnum = (double)current.getLong("pressure_in");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ////////////////////////////////
        pressure = "Air Pressure: " + presnum + "inHg";
        ////////////////////////////////

        double humid = 0;
        try {
            humid = (double)current.getLong("humidity");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ////////////////////////////////
        humidity = "Humidity: " + humid + "%";
        ////////////////////////////////

        double precipnum = 0;

        try {
            precipnum = (double)current.getLong("precip_in");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ////////////////////////////////
        precip = "Precipitation: " + precipnum + "inches";
        ////////////////////////////////

        Log.d("ME", "********* " + address);
        Log.d("ME", "********* " + updatedat);
        Log.d("ME", "********* " + weatherdescript);
        Log.d("ME", "********* " + tempmain);
        Log.d("ME", "********* " + fl);
        Log.d("ME", "********* " + cc);
        Log.d("ME", "********* " + windSpeed);
        Log.d("ME", "********* " + pressure);
        Log.d("ME", "********* " + humidity);
        Log.d("ME", "********* " + precip);

        mainActivity.location.setText(address);
        mainActivity.update.setText(updatedat);
        mainActivity.status.setText(weatherdescript);
        mainActivity.temp.setText(tempmain);
        mainActivity.feelslike.setText(fl);
        mainActivity.precipitation.setText(precip);
        mainActivity.cloudcast.setText(cc);
        mainActivity.windsp.setText(windSpeed);
        mainActivity.pressuresp.setText(pressure);
        mainActivity.humiditysp.setText(humidity);

    }



}











