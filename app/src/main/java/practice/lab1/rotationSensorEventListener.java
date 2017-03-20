package practice.lab1;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.icu.text.DecimalFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.TextView;

import static android.content.ContentValues.TAG;

/**
 * Created by anmolarora on 20/03/17.
 */


public class rotationSensorEventListener implements SensorEventListener {
    TextView output;
    double x,y,z;



    public rotationSensorEventListener(TextView output){
        this.output = output;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onSensorChanged(SensorEvent event) {
        String value = String.format("(%f,%f,%f)",x,y,z);

        if(event.sensor.getType()== Sensor.TYPE_ROTATION_VECTOR){
            x = event.values[0];
            y = event.values[1];
            z = event.values[2];
            DecimalFormat precision =  new DecimalFormat("0.00");


            output.setText("Rotation Sensor Reading: \n"+"("+ precision.format(x)+", "+ precision.format(y)+", "+ precision.format(z)+")");



        }


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
