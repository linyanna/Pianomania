package com.example.pianomania;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class XylophoneActivity extends AppCompatActivity {

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
    private int mC1Sound;
    private int mD1Sound;
    private int mE1Sound;
    private int mF1Sound;
    private int mG1Sound;
    private int mA1Sound;
    private int mB1Sound;
    private int mC2Sound;

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
        setContentView(R.layout.activity_xylophone);

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
        mC1Sound = mSoundPool.load(getApplicationContext(), R.raw.c1_xylophone, 1);
        mD1Sound = mSoundPool.load(getApplicationContext(), R.raw.d1_xylophone, 1);
        mE1Sound = mSoundPool.load(getApplicationContext(), R.raw.e1_xylophone, 1);
        mF1Sound = mSoundPool.load(getApplicationContext(), R.raw.f1_xylophone, 1);
        mG1Sound = mSoundPool.load(getApplicationContext(), R.raw.g1_xylophone, 1);
        mA1Sound = mSoundPool.load(getApplicationContext(), R.raw.a1_xylophone, 1);
        mB1Sound = mSoundPool.load(getApplicationContext(), R.raw.b1_xylophone, 1);
        mC2Sound = mSoundPool.load(getApplicationContext(), R.raw.c2_xylophone, 1);

        // Get Ids for buttons
        button1 = (Button)findViewById(R.id.c1_xylophone_key);
        button2 = (Button)findViewById(R.id.d1_xylophone_key);
        button3 = (Button)findViewById(R.id.e1_xylophone_key);
        button4 = (Button)findViewById(R.id.f1_xylophone_key);
        button5 = (Button)findViewById(R.id.g1_xylophone_key);
        button6 = (Button)findViewById(R.id.a1_xylophone_key);
        button7 = (Button)findViewById(R.id.b1_xylophone_key);
        button8 = (Button)findViewById(R.id.c2_xylophone_key);

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

    public void c1_xylophone (View v){
        mSoundPool.play(mC1Sound, lft_vol, rgt_vol, prty,loop,NORMAL_PLAY_RATE);
    }
    public void d1_xylophone (View v){
        mSoundPool.play(mD1Sound, lft_vol, rgt_vol, prty,loop,NORMAL_PLAY_RATE);
    }
    public void e1_xylophone (View v){
        mSoundPool.play(mE1Sound, lft_vol, rgt_vol, prty,loop,NORMAL_PLAY_RATE);
    }
    public void f1_xylophone (View v){
        mSoundPool.play(mF1Sound, lft_vol, rgt_vol, prty,loop,NORMAL_PLAY_RATE);
    }
    public void g1_xylophone (View v){
        mSoundPool.play(mG1Sound, lft_vol, rgt_vol, prty,loop,NORMAL_PLAY_RATE);
    }
    public void a1_xylophone (View v){
        mSoundPool.play(mA1Sound, lft_vol, rgt_vol, prty,loop,NORMAL_PLAY_RATE);
    }
    public void b1_xylophone (View v){
        mSoundPool.play(mB1Sound, lft_vol, rgt_vol, prty,loop,NORMAL_PLAY_RATE);
    }
    public void c2_xylophone (View v){
        mSoundPool.play(mC2Sound, lft_vol, rgt_vol, prty,loop,NORMAL_PLAY_RATE);
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