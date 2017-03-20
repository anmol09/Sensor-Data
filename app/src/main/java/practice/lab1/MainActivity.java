package practice.lab1;

import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout layout = (LinearLayout)findViewById(R.id.activity_main);

        TextView light_text = (TextView)findViewById(R.id.textView);
        TextView acc_text = (TextView)findViewById(R.id.textView3);
        TextView magnetic_text = (TextView)findViewById(R.id.textView4);
        TextView rotation_text = (TextView)findViewById(R.id.textView5);


        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        Sensor lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);  // Declaring the light sensors
        Sensor accelerometerSensor =  sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        Sensor magneticSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        Sensor rotationSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);

        SensorEventListener l = new LightSensorEventListener(light_text); // Declaring light sensors
        SensorEventListener acc = new accelerometerSensorEventListener(acc_text);
        SensorEventListener magnetic = new magneticSensorEventListener(magnetic_text);
        SensorEventListener rotation = new rotationSensorEventListener(rotation_text);


        sensorManager.registerListener(l,lightSensor,SensorManager.SENSOR_DELAY_NORMAL); // Registering sensors
        sensorManager.registerListener(acc,accelerometerSensor,SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(magnetic,magneticSensor,SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(rotation,rotationSensor,SensorManager.SENSOR_DELAY_NORMAL);







    }
}
