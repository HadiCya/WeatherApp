package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import android.widget.Toast;

public class LocationScreen extends AppCompatActivity {

    BottomNavigationView navigation;

    Button enter;
    EditText textinput;
    String txtl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_screen);


        navigation = findViewById(R.id.bottom_navigation);
        enter = findViewById(R.id.enterbtn);
        textinput = findViewById(R.id.textbox);
        navigation.setSelectedItemId(R.id.locate);


        navigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();
                switch (id){
                    case R.id.locate:
                        return true;

                    case R.id.weather:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
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

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtl = textinput.getText().toString();
                MainActivity.locate = txtl;
                Toast toast = Toast.makeText(LocationScreen.this,"New location set, see weather tab",Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP,0,250);
                toast.show();

            }
        });
    }

}