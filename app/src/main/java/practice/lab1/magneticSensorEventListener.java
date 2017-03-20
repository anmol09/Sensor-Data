package practice.lab1;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.widget.TextView;

/**
 * Created by anmolarora on 20/03/17.
 */

public class magneticSensorEventListener implements SensorEventListener {
    TextView output;
    double x,y,z;
    String value = String.format("(%f,%f,%f)",x,y,z);



    public magneticSensorEventListener(TextView output){
        this.output = output;

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType()== Sensor.TYPE_MAGNETIC_FIELD){
            x = event.values[0];
            y = event.values[1];
            z = event.values[2];

            output.setText(value);


        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
