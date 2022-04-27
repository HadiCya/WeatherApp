package com.example.finalproject;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MyTask extends AsyncTask<Void,Void,String> {
    public MainActivity MainActivity;
    public String address = " ";
    public String updatedat = " ";
    public String weatherdescript = " ";
    public String tempmain = " ";
    public String precip = " ";
    public String cc = " ";
    public String fl = " ";
    public String windSpeed = " ";
    public String pressure = " ";
    public String humidity = " ";
    public String locate = " ";
    public String[] fnllist = new String[10];

//    TextView location;
//    TextView update;
//    TextView status ;
//    TextView temp ;
//    TextView precipitation;
//    TextView cloudcast ;
//    TextView feelslike;
//    TextView windsp ;
//    TextView pressuresp;
//    TextView humiditysp ;

//
//    location = findViewById(R.id.location);
//    update = findViewById(R.id.update);
//    status = findViewById(R.id.status);
//    temp = findViewById(R.id.temp);
//    feelslike = findViewById(R.id.feelslike);
//    precipitation = findViewById(R.id.precipitation);
//    cloudcast = findViewById(R.id.cloudcast);
//    windsp = findViewById(R.id.wind);
//    pressuresp = findViewById(R.id.pressure);
//    humiditysp = findViewById(R.id.humidity);

    String urlStr;

    public MyTask(MainActivity activity_in, String address_in, String updatedat_in, String weatherdescript_in,
                  String tempmain_in, String precip_in, String cc_in, String fl_in,
                  String windSpeed_in, String pressure_in, String humidity_in, String locate_in) {

        this.MainActivity = activity_in;
        this.address = address_in;
        this.updatedat = updatedat_in;
        this.weatherdescript = weatherdescript_in;
        this.tempmain = tempmain_in;
        this.precip = precip_in;
        this.cc = cc_in;
        this.fl = fl_in;
        this.windSpeed = windSpeed_in;
        this.pressure = pressure_in;
        this.humidity = humidity_in;
        this.locate = locate_in;




    }

    @Override
    protected String doInBackground(Void... params) {
        String jsonStr = "";
        urlStr = ("https://api.weatherapi.com/v1/current.json?key=0db3e6d81c4f426db8a175734222604&q=48128&aqi=no");
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

        this.fnllist = new String[]{address, updatedat, weatherdescript, tempmain, fl, cc, windSpeed, pressure, humidity, precip};


//        location.setText(address);
//        update.setText(updatedat);
//        status.setText(weatherdescript);
//        temp.setText(tempmain);
//        feelslike.setText(fl);
//        precipitation.setText(precip);
//        cloudcast.setText(cc);
//        windsp.setText(windSpeed);
//        pressuresp.setText(pressure);
//        humiditysp.setText(humidity);




    }



}











