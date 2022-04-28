package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.Calendar;


public class AlarmScreen extends AppCompatActivity {


    BottomNavigationView navigation;
    Button alarmset;

    TimePicker time;

    private Calendar calendar;
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;

    int minute;
    int hour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createNotificationChannel();
        setContentView(R.layout.activity_alarm_screen);

        navigation = findViewById(R.id.bottom_navigation);
        alarmset = findViewById(R.id.setbtn);
        time = findViewById(R.id.timepick);

        navigation.setSelectedItemId(R.id.alarm);

        navigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();
                switch (id){
                    case R.id.alarm:
                        return true;

                    case R.id.locate:
                        startActivity(new Intent(getApplicationContext(), LocationScreen.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.weather:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return true;
            }
        });


        alarmset.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {

                minute = time.getMinute();
                hour = time.getHour();

                calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY,hour);
                calendar.set(Calendar.MINUTE,minute);
                calendar.set(Calendar.SECOND,0);
                calendar.set(Calendar.MILLISECOND,0);

                alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

                Intent myIntent = new Intent(AlarmScreen.this,AlarmReceiver.class);

                pendingIntent = PendingIntent.getBroadcast(AlarmScreen.this, 0, myIntent, 0);
                alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);

                Log.d("ME", "*******" + hour + ":" + minute + "********");


            }
        });



    }
    private void createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "mahdi";
            String description = "Channel for Alarm Manager";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel("mahdi",name,importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }


}