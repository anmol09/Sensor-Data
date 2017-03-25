package practice.lab1;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.icu.text.DecimalFormat;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import static android.content.ContentValues.TAG;

/**
 * Created by anmolarora on 20/03/17.
 */

public class accelerometerSensorEventListener implements SensorEventListener {
    TextView output;
    double x,y,z;
    LineGraphView graph;
    Button saveReading;
    private String[][] readings = new String [100][3];
    public int counter = 0;
    public boolean flag = false;

    private String filename = "SampleData.csv";
    private String filepath = "MyFileStorage";
    File myExternalFile;



    public accelerometerSensorEventListener(TextView output, LineGraphView graph, Button saveReading){
        this.output = output;
        this.graph  = graph;
        this.saveReading = saveReading;


    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onSensorChanged(SensorEvent event) {



        String value = String.format("(%f,%f,%f)",x,y,z);


        if(event.sensor.getType()== Sensor.TYPE_ACCELEROMETER){
            graph.addPoint(event.values);

            x = event.values[0];
            y = event.values[1];
            z = event.values[2];







            DecimalFormat precision =  new DecimalFormat("0.00");



            readings[counter][0]= precision.format(x);
            readings[counter][1]= precision.format(y);
            readings[counter][2]= precision.format(z);



            counter++ ;

            if(counter>98){
                flag =true;
            }
            if(flag){
                String temp1 = null;

                for( int i=99; i>0;i++){
                    temp1 = readings[i-1][0];
                    readings[i][0] = temp1;

                    temp1 = readings[i-1][1];
                    readings[i][1]= temp1;

                    temp1 = readings[i-1][2];
                    readings[i][2]= temp1;

                }
                counter = 0 ;

            }

            saveReading.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        myExternalFile = new File(Environment.getExternalStorageDirectory()+"Data1.csv");
                        if(myExternalFile.createNewFile()){
                            Log.d(TAG, "File created ");
                        }

                        PrintWriter pw = new PrintWriter(myExternalFile);
                        for (int i = 0; i < 99; i++) {
                            pw.println(readings[i]);
                        }
                        pw.close();
                    }catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });



            output.setText("Accelerometer Sensor Reading: \n"+"("+ precision.format(x)+", "+ precision.format(y)+", "+ precision.format(z)+")");



        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
