package com.example.pianomania;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.SoundPool;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class PianoActivity extends AppCompatActivity {

    // Shared preferences
    public AppPreferences ap;
    private float lightThreshold = 5000;

    // Sensor variables
    private SensorManager sensorManager;
    private Sensor sensorLight;

    // Helpful Constants
    private final int sim_sound = 7;
    private final float lft_vol = 1.0f;
    private final float rgt_vol = 1.0f;
    private final int loop = 0;
    private final int prty = 0;
    private final float NORMAL_PLAY_RATE = 1.0f;

    // Add member variables here
    private SoundPool mSoundPool;
    private int mA3Sound;
    private int mB3Sound;
    private int mC4Sound;
    private int mD4Sound;
    private int mE4Sound;
    private int mF4Sound;
    private int mG4Sound;
    private int mA4Sound;

    // Buttons
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private String keyColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piano);

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


        // Create a new SoundPool
        mSoundPool = new SoundPool(sim_sound, AudioManager.STREAM_MUSIC, 0);

        // Load and get the IDs to identify the sounds
        mA3Sound = mSoundPool.load(getApplicationContext(), R.raw.a3_piano, 1);
        mB3Sound = mSoundPool.load(getApplicationContext(), R.raw.b3_piano, 1);
        mC4Sound = mSoundPool.load(getApplicationContext(), R.raw.c4_piano, 1);
        mD4Sound = mSoundPool.load(getApplicationContext(), R.raw.d4_piano, 1);
        mE4Sound = mSoundPool.load(getApplicationContext(), R.raw.e4_piano, 1);
        mF4Sound = mSoundPool.load(getApplicationContext(), R.raw.f4_piano, 1);
        mG4Sound = mSoundPool.load(getApplicationContext(), R.raw.g4_piano, 1);
        mA4Sound = mSoundPool.load(getApplicationContext(), R.raw.a4_piano, 1);

        // Get Ids for buttons
        button1 = (Button)findViewById(R.id.a3_piano_key);
        button2 = (Button)findViewById(R.id.b3_piano_key);
        button3 = (Button)findViewById(R.id.c4_piano_key);
        button4 = (Button)findViewById(R.id.d4_piano_key);
        button5 = (Button)findViewById(R.id.e4_piano_key);
        button6 = (Button)findViewById(R.id.f4_piano_key);
        button7 = (Button)findViewById(R.id.g4_piano_key);
        button8 = (Button)findViewById(R.id.a4_piano_key);

        changeButtonColor();
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


    public void a3_piano (View v){
        mSoundPool.play(mA3Sound, lft_vol, rgt_vol, prty,loop,NORMAL_PLAY_RATE);
    }
    public void b3_piano (View v){
        mSoundPool.play(mB3Sound, lft_vol, rgt_vol, prty,loop,NORMAL_PLAY_RATE);
    }
    public void c4_piano (View v){
        mSoundPool.play(mC4Sound, lft_vol, rgt_vol, prty,loop,NORMAL_PLAY_RATE);
    }
    public void d4_piano (View v){
        mSoundPool.play(mD4Sound, lft_vol, rgt_vol, prty,loop,NORMAL_PLAY_RATE);
    }
    public void e4_piano (View v){
        mSoundPool.play(mE4Sound, lft_vol, rgt_vol, prty,loop,NORMAL_PLAY_RATE);
    }
    public void f4_piano (View v){
        mSoundPool.play(mF4Sound, lft_vol, rgt_vol, prty,loop,NORMAL_PLAY_RATE);
    }
    public void g4_piano (View v){
        mSoundPool.play(mG4Sound, lft_vol, rgt_vol, prty,loop,NORMAL_PLAY_RATE);
    }
    public void a4_piano (View v){
        mSoundPool.play(mA4Sound, lft_vol, rgt_vol, prty,loop,NORMAL_PLAY_RATE);
    }

    public void changeButtonColor() {
        keyColor = ap.getStr("keyColor");
        button1.setBackgroundColor(Color.parseColor(keyColor));
        button2.setBackgroundColor(Color.parseColor(keyColor));
        button3.setBackgroundColor(Color.parseColor(keyColor));
        button4.setBackgroundColor(Color.parseColor(keyColor));
        button5.setBackgroundColor(Color.parseColor(keyColor));
        button6.setBackgroundColor(Color.parseColor(keyColor));
        button7.setBackgroundColor(Color.parseColor(keyColor));
        button8.setBackgroundColor(Color.parseColor(keyColor));
    }

    public void onBackPressed(View view) {
        finish();
    }
}