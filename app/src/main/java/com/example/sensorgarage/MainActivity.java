package com.example.sensorgarage;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private Button btnStart;
    private Button btnStop;
    private TextView textView;
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private SensorEventListener sensorListner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnStart = (Button) findViewById(R.id.btnStart);
        btnStop = (Button) findViewById(R.id.btnStop);
        textView = (TextView) findViewById(R.id.xReading);
        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorListner = this;




        btnStart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                mSensorManager.registerListener(sensorListner, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                mSensorManager.unregisterListener(sensorListner);
            }
        });

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
            textView.setText("X: " + sensorEvent.values[0]+ "\nY: "+sensorEvent.values[1]+"\nZ :"+sensorEvent.values[0]);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
