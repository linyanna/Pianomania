package com.example.pianomania;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    // Shared preferences variables
    public AppPreferences ap;
    private float lightThreshold = 5000;

    // Sensor variables
    private SensorManager sensorManager;
    private Sensor sensorLight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get shared preferences
        ap = new AppPreferences(this);
        lightThreshold = ap.getFloat("lightThreshold");

        // Create sensor
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensorLight = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        SensorEventListener sensorEventListenerLight = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                float floatSensorValue = sensorEvent.values[0];
                changeDayNightTheme(floatSensorValue, lightThreshold);
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {
            }
        };

        sensorManager.registerListener(sensorEventListenerLight, sensorLight, SensorManager.SENSOR_DELAY_NORMAL);
    }

    private void changeDayNightTheme(float floatSensorValue, float lightThreshold) {
        if (floatSensorValue < lightThreshold) {
            Log.d("Example:", "onSensorChanged: it is dark");
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        else {
            Log.d("Example:", "onSensorChanged: it is light");
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    public void onPianoPressed(View view) {
        Intent myIntent = new Intent(getBaseContext(), PianoActivity.class);
        startActivity(myIntent);
    }
    public void onXylophonePressed(View view) {
        Intent myIntent = new Intent(getBaseContext(), XylophoneActivity.class);
        startActivity(myIntent);
    }
    public void onSettingsPressed(View view) {
        Intent myIntent = new Intent(getBaseContext(), SettingsActivity.class);
        startActivity(myIntent);
    }
}