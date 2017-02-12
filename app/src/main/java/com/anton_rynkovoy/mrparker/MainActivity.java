package com.anton_rynkovoy.mrparker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sPref;
    private boolean isCityChosen = false;
    private final String SAVED_CITY_NAME = "saved_city";
    private final String SAVED_CITY_STATE = "saved_city_state";

    Spinner spinnerCities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sPref = getPreferences(MODE_PRIVATE);
        isCityChosen = sPref.getBoolean(SAVED_CITY_STATE,false);
        if(isCityChosen) {
            selectedCity(sPref.getString(SAVED_CITY_NAME, "Kiev"));
        }
        setContentView(R.layout.activity_main);
        spinnerCities = (Spinner) findViewById(R.id.spinnerCities);
    }

    public void onSetCity(View view) {
        selectedCity(spinnerCities.getSelectedItem()+"");
        saveCity();
    }

    public void selectedCity(String city){
        Intent intent;
        switch (city){
            case "Odessa":
                intent = new Intent(this, GoogleMapActivity.class);
                intent.putExtra("lat", 46.482989);
                intent.putExtra("lon", 30.735516);
                startActivity(intent);
                break;
            case "Kiev":
                intent = new Intent(this, GoogleMapActivity.class);
                intent.putExtra("lat", 50.493786);
                intent.putExtra("lon", 30.527314);
                startActivity(intent);
                break;
            case "Nikolaev":
                intent = new Intent(this, GoogleMapActivity.class);
                intent.putExtra("lat", 46.974175);
                intent.putExtra("lon", 32.010469);
                startActivity(intent);
                break;
        }
    }

    public void saveCity(){
        sPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor saveCity = sPref.edit();
        saveCity.putString(SAVED_CITY_NAME, spinnerCities.getSelectedItem()+"");
        saveCity.putBoolean(SAVED_CITY_STATE, true);
        saveCity.apply();
    }
}
