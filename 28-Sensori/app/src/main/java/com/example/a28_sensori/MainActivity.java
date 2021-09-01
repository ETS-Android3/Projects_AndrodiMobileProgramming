package com.example.a28_sensori;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity implements SensorEventListener{

    private static final long INTERVALLO = 250;
    private static final float UPDATE_WEIGHT = 0.8f;
    private TextView tv_accellerazione_x, tv_accellerazione_y, tv_accellerazione_z;
    private TextView tv_variazione_x, tv_variazione_y, tv_variazione_z;
    private TextView tv_media_x, tv_media_y, tv_media_z;
    private float media_x = 0, media_y = 0, media_z = 0;

    private SensorManager sensorManager;
    private Sensor accellerometro;

    private boolean first_update = true;

    private long lastUpdate = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_accellerazione_x = findViewById(R.id.tv_accellerazione_x);
        tv_accellerazione_y = findViewById(R.id.tv_accellerazione_y);
        tv_accellerazione_z = findViewById(R.id.tv_accellerazione_z);

        tv_variazione_x = findViewById(R.id.tv_var_x);
        tv_variazione_y = findViewById(R.id.tv_var_y);
        tv_variazione_z = findViewById(R.id.tv_var_z);

        tv_media_x = findViewById(R.id.tv_media_x);
        tv_media_y = findViewById(R.id.tv_media_y);
        tv_media_z = findViewById(R.id.tv_media_z);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accellerometro = sensorManager.getDefaultSensor(SensorManager.SENSOR_ACCELEROMETER);

        if (accellerometro == null) {
            finish();
        }
    }

    @Override
    protected void onResume() {
        sensorManager.registerListener( this, accellerometro, SensorManager.SENSOR_DELAY_UI);
        lastUpdate = 0;
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == SensorManager.SENSOR_ACCELEROMETER) {

            long now = System.currentTimeMillis();

            if (now -lastUpdate > INTERVALLO) {
                float x, y, z;
                x = sensorEvent.values[0];
                y = sensorEvent.values[1];
                z = sensorEvent.values[2];

                aggiorna_media(x, y, z);

                tv_accellerazione_x.setText("asse X: " + String.format("%7.4f", x));
                tv_accellerazione_y.setText("asse Y: " + String.format("%7.4f", y));
                tv_accellerazione_z.setText("asse Z: " + String.format("%7.4f", z));

                tv_media_x.setText("asse X: " + String.format("%7.4f", media_x));
                tv_media_y.setText("asse Y: " + String.format("%7.4f", media_y));
                tv_media_z.setText("asse Z: " + String.format("%7.4f", media_z));

                tv_variazione_x.setText("asse X: " + String.format("%7.4f", (x-media_x)));
                tv_variazione_y.setText("asse Y: " + String.format("%7.4f", (y-media_y)));
                tv_variazione_z.setText("asse Z: " + String.format("%7.4f", (z-media_z)));

                lastUpdate = now;
            }
        }
    }

    private void aggiorna_media(float x, float y, float z) {
        if (first_update) {
            media_x = x;
            media_y = y;
            media_z = z;
            first_update = false;
        } else {
            media_x = UPDATE_WEIGHT * x * (1-UPDATE_WEIGHT)*media_x;
            media_y = UPDATE_WEIGHT * y * (1-UPDATE_WEIGHT)*media_y;
            media_z = UPDATE_WEIGHT * z * (1-UPDATE_WEIGHT)*media_z;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }
}