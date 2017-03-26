package practice.lab1;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by anmolarora on 20/03/17.
 */

public class LightSensorEventListener implements SensorEventListener {
    TextView output;
    double lightIntensity;

    public LightSensorEventListener(TextView output){
        this.output = output;

    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        Log.d("Function Called", "onSensorChanged:Accelerometer ");


        if(event.sensor.getType()==Sensor.TYPE_LIGHT){
            lightIntensity = event.values[0];
            output.setText("Light Sensor Reading: \n"+String.valueOf(lightIntensity));


        }


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
