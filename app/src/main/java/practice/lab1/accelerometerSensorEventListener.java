package practice.lab1;

import android.content.Context;
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
import java.io.IOException;
import java.io.PrintWriter;


/**
 * Created by anmolarora on 20/03/17.
 */

public class accelerometerSensorEventListener implements SensorEventListener {
    TextView output;
    double x,y,z;
    LineGraphView graph;
    Button saveReading;
    private static String[][] readings = new String[100][3];
    public static int counter = 0;
    public static boolean flag = false;
    private Context c;





    public accelerometerSensorEventListener(Context c, TextView output, LineGraphView graph, Button saveReading){
        this.output = output;
        this.graph  = graph;
        this.saveReading = saveReading;
        this.c = c;
    }

    public void writeToFile() {
        File file;
        PrintWriter printWriter = null;
        try {

                 file = new File(c.getExternalFilesDir("LAB1"),"Data1.csv");
                 printWriter = new PrintWriter(file);

            for(int i = 0; i<100; i++){
                printWriter.println(readings[i]);
            }

        } catch (IOException e){

                Log.d("Data", "File Write Fail: " + e.toString());
            }
            finally{

                if(printWriter != null) {    // NULL pointer exception handling
                printWriter.flush();
                printWriter.close();
            }

            //Notice that we are using Log.d.
            Log.d("Data", "File Write Ended.");
        }


        }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void writeToArray() {

        DecimalFormat precision =  new DecimalFormat("0.00");


        if(!flag) {

                readings[counter][0] = precision.format(x);
                readings[counter][1] = precision.format(y);
                readings[counter][2] = precision.format(z);
            }

        if(flag && (counter>99)){
            readings[0][0]= precision.format(x);
            readings[0][1]= precision.format(y);
            readings[0][2]= precision.format(z);
        }


            counter++ ;

            if(counter>99) {
                flag =true;
            }

            if(flag){
                String temp1 = null;

                for( int i=99; i>0;i--){
                    temp1 = readings[i-1][0] + ",";
                    readings[i][0] = temp1;

                    temp1 = readings[i-1][1] + ",";
                    readings[i][1]= temp1;

                    temp1 = readings[i-1][2];
                    readings[i][2]= temp1;

                }


            }

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



           writeToArray();

            saveReading.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    writeToFile();
                }

            });



            output.setText("Accelerometer Sensor Reading: \n"+"("+ precision.format(x)+", "+ precision.format(y)+", "+ precision.format(z)+")");



        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
