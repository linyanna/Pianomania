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
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity {

    // Shared preferences
    public AppPreferences ap;
    private SeekBar seekBar;
    private float lightThreshold;
    private TextView textView;
    private EditText editText;

    // Sensor variables
    private SensorManager sensorManager;
    private Sensor sensorLight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // Create and get shared preferences
        ap = new AppPreferences(this);
        seekBar = (SeekBar)findViewById(R.id.light_threshold_seekbar);
        textView = (TextView)findViewById(R.id.light_threshold_text);
        editText = (EditText)findViewById(R.id.color_plain_text_view);

        lightThreshold = ap.getFloat("lightThreshold");
        seekBar.setProgress((int)ap.getFloat("lightThreshold"));
        textView.setText("Set night mode when below " + (int)lightThreshold + " lumens.");

        // Create sensor
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensorLight = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        // Listeners
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                ap.setFloat("lightThreshold", progress);

                lightThreshold = ap.getFloat("lightThreshold");
                seekBar.setProgress((int)ap.getFloat("lightThreshold"));
                textView.setText("Set night mode when below " + (int)lightThreshold + " lumens.");

                changeDayNightTheme(ap.getFloat("lightSensorValue"), lightThreshold);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        SensorEventListener sensorEventListenerLight = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                float lightSensorValue = sensorEvent.values[0];
                ap.setFloat("lightSensorValue", lightSensorValue);
                changeDayNightTheme(ap.getFloat("lightSensorValue"), lightThreshold);
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {
            }
        };

        sensorManager.registerListener(sensorEventListenerLight, sensorLight, SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void onColorChangeClick(View view) {
        String colorString = String.valueOf(editText.getText());
        ap.setStr("keyColor", colorString);
        Log.d("Example: ", ap.getStr("keyColor"));
    }

    private void changeDayNightTheme(float lightSensorValue, float lightThreshold) {
        if (lightSensorValue < lightThreshold) {
            Log.d("Example:", "onSensorChanged: it is dark");
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        else {
            Log.d("Example:", "onSensorChanged: it is light");
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    public void onBackPressed(View view) {
        Log.d("Example: ", "light threshold back press: " + lightThreshold);
        finish();
    }
}