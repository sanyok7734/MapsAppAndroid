package com.odintsov.mapsapp;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.odintsov.mapsapp.network.GpsController;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private Button coordinate;
    private EditText lenght;
    private ImageView compass;

    private TextView bearingText;

    private SensorManager mSensorManager;
    private float currentDegree = 0f;
    private GpsController gpsController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        coordinate = (Button) findViewById(R.id.coordinates);
        lenght = (EditText) findViewById(R.id.length);
        compass = (ImageView) findViewById(R.id.image_Compass);
        bearingText = (TextView) findViewById(R.id.bearingText);

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        gpsController = new GpsController(MainActivity.this);

        coordinate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkGPS()) {
                    GPSTracker gps = new GPSTracker(MainActivity.this);
                    if (gps.canGetLocation) {
                        double latitude = gps.getLatitude();
                        double longitude = gps.getLongitude();
                        float bearing = Float.parseFloat(bearingText.getText().toString());
                        gpsController.sendCoordinates(longitude, latitude, bearing, Double.parseDouble(lenght.getText().toString()));
                        Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude + "\nBearing: " + bearing, Toast.LENGTH_LONG).show();
                    } else {
                        gps.showSettingsAlert();
                    }
                }
            }
        });
    }


    //проверяем доступность gps
    private boolean checkGPS() {
        String provider = Settings.Secure.getString(getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
        if (provider.equals("")) {
            Toast.makeText(MainActivity.this, "Check enable GPS", Toast.LENGTH_SHORT).show();
            checkEnableGPS();
            return false;
        } else {
            return true;
        }
    }

    //Переходим в настройки что бы включить определение местоположения
    //(если gps выключен)
    public void checkEnableGPS() {
        Intent gpsOptionsIntent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivity(gpsOptionsIntent);
    }


    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
                SensorManager.SENSOR_DELAY_GAME);

    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        float degree = Math.round(event.values[0]);

        bearingText.setText(""+Float.toString(degree));
        RotateAnimation ra = new RotateAnimation(currentDegree, -degree, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        ra.setDuration(210);
        ra.setFillAfter(true);
        compass.startAnimation(ra);
        currentDegree = -degree;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
