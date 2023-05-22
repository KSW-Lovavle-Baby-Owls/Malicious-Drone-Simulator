package com.dji.sdk.maldronesim;

import static dji.log.GlobalConfig.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;

import dji.common.flightcontroller.Attitude;
import dji.common.flightcontroller.FlightControllerState;
import dji.common.flightcontroller.LocationCoordinate3D;
import dji.sdk.flightcontroller.FlightController;
import dji.sdk.products.Aircraft;
import dji.sdk.sdkmanager.DJISDKManager;

public class MainActivity extends AppCompatActivity {
    TSPI mTSPI;
    TextView logs;
    FlightController flightController;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTSPI = new TSPI();
        mContext = getApplicationContext();
        logs = (TextView) findViewById(R.id.view_log);
        initFlightControllerState();
    }
    public void writeLogfile(Context context, String filename, String content){
        String data = content;
        FileOutputStream outputStream;
        try{
            outputStream = context.openFileOutput(filename, Context.MODE_APPEND);
            outputStream.write(data.getBytes());
            outputStream.close();
            Log.d("filewrite","success");
        }catch(IOException e){
            Log.d("filewrite","failed");
            e.printStackTrace();
        }
    }
    private void initFlightControllerState(){
        String fileName = ("FlightLog: " + Calendar.getInstance().getTime()+".csv");

        Log.d("FlightControllerState", "connecting FlightController");

        try {
            flightController = ((Aircraft) DJISDKManager.getInstance().getProduct()).getFlightController();
        } catch (Exception e) {
            Log.d("FlightControllerState","not Connected");
        }

        if(flightController == null){
            Log.d("FlightControllerState","not Connected");
        } else {
            flightController.setStateCallback(new FlightControllerState.Callback() {
                @Override
                public void onUpdate(@NonNull FlightControllerState djiFlightControllerCurrentState) {

                    FirebaseFirestore db = FirebaseFirestore.getInstance();

                    LocationCoordinate3D locationCoordinate3D = djiFlightControllerCurrentState.getAircraftLocation();
                    Attitude attitude = djiFlightControllerCurrentState.getAttitude();

                    mTSPI.setTimestamp(Calendar.getInstance().getTime());
                    mTSPI.setGpsSignalStrength(String.valueOf(djiFlightControllerCurrentState.getGPSSignalLevel()));
                    mTSPI.setSatelliteCount(djiFlightControllerCurrentState.getSatelliteCount());
                    mTSPI.setCurrentLatitude(locationCoordinate3D.getLatitude());
                    mTSPI.setCurrentLongitude(locationCoordinate3D.getLongitude());

                    mTSPI.setCurrentAltitude(locationCoordinate3D.getAltitude());

                    mTSPI.setPitch(attitude.pitch);
                    mTSPI.setYaw(attitude.yaw);

                    writeLogfile(mContext,fileName,mTSPI.logResults());
                    Log.d("(Thread)TSPILogger", "hello from logger");

                    HashMap result = new HashMap<>();
                    result.put("Time",  Calendar.getInstance().getTime());
                    result.put("GpsSignal", String.valueOf(djiFlightControllerCurrentState.getGPSSignalLevel()));
                    result.put("Altitude", locationCoordinate3D.getAltitude());
                    result.put("Latitude", locationCoordinate3D.getLatitude());
                    result.put("Longitude", locationCoordinate3D.getLongitude());
                    result.put("Pitch", attitude.pitch);
                    result.put("Yaw", attitude.yaw);

                    if (!(Float.isNaN((float)result.get("Latitude"))) && !(Float.isNaN((float)result.get("Longitude")))){
                        db.collection("mal_test1").add(result).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Error adding document", e);
                            }
                        });
                    }
                    else{
                        System.out.println("Latitude and Logitude are NaN");
                    }
//                    db.collection("mal_drone_test1").add(result).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                        @Override
//                        public void onSuccess(DocumentReference documentReference) {
//                            Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
//                        }
//                    }).addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//                            Log.w(TAG, "Error adding document", e);
//                        }
//                    });

                }
            });
        }
    }
}