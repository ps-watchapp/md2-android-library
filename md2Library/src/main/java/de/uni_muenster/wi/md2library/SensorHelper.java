package de.uni_muenster.wi.md2library;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import de.uni_muenster.wi.md2library.controller.action.implementation.customCode.Md2AttributeSetTask;
import de.uni_muenster.wi.md2library.controller.action.implementation.customCode.Md2TaskQueue;
import de.uni_muenster.wi.md2library.exception.Md2WidgetNotCreatedException;
import de.uni_muenster.wi.md2library.model.type.implementation.Md2Sensor;

/**
 * Created by Tobias Nauber on 28.05.2017.
 * PS-Watchapp
 */

public class SensorHelper {
    Context mContext;

    private Float sensorVal;
    private String varName;
    private String sensorType;

    //SensorenManager
    private SensorManager mSensorManager;
    private Sensor mSensor;

    //Konstruktor damit getSystemService funktioniert, da diese Klasse keine Activity ist muss der Context übergeben werden
    public SensorHelper(Context mContext, String _varName, String _sensorType) {
        this.mContext = mContext;
        this.varName = _varName;
        this.sensorType = _sensorType;

        mSensorManager = (SensorManager) mContext.getSystemService(mContext.SENSOR_SERVICE);

        if(sensorType.equals("accelerometer")){
            mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        }
        if(sensorType.equals("proximity")){
            mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        }
        if(sensorType.equals("gyroskop")){
            mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        }
        if(sensorType.equals("heartrate")){
            mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_HEART_RATE);
        }

        //Eventlistener für den Sensor
        SensorEventListener _SensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                setSensorValue(event.values[0]);
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };

        //Prüfen ob SensorManager vorhanden
        if(mSensorManager!=null){
            //Prüfen ob der entsprechende Sensor überhaupt verfügbar ist siehe Initialisierung
            if(mSensor != null){
                mSensorManager.registerListener(_SensorEventListener, mSensor, mSensorManager.SENSOR_DELAY_NORMAL);
                System.out.println("Sensor:" + this.sensorType + " gefunden:");
            }
            else{
                System.out.println("Sensor:" + this.sensorType + " nicht gefunden!!!");
                setSensorValue(Float.parseFloat("-99999.99"));
            }
        }
    }

    //ParameterKonstruktor für Axis
    public SensorHelper(Context mContext, String _varName, String _sensorType, final String Axis) {
        this.mContext = mContext;
        this.varName = _varName;
        this.sensorType = _sensorType;

        mSensorManager = (SensorManager) mContext.getSystemService(mContext.SENSOR_SERVICE);

        if(sensorType.equals("accelerometer")){
            mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        }
        if(sensorType.equals("gyroskop")){
            mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        }
        //Eventlistener für den Sensor
        SensorEventListener _SensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                if(Axis.equals("X")){
                    setSensorValue(event.values[0]);
                }
                if(Axis.equals("Y")){
                    setSensorValue(event.values[1]);
                }
                if(Axis.equals("Z")){
                    setSensorValue(event.values[2]);
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };

        //Prüfen ob SensorManager vorhanden
        if(mSensorManager!=null){
            //Prüfen ob der entsprechende Sensor überhaupt verfügbar ist sieh Initialisierung
            if(mSensor != null){
                mSensorManager.registerListener(_SensorEventListener, mSensor, mSensorManager.SENSOR_DELAY_NORMAL);
                System.out.println("Sensor:" + this.sensorType + " gefunden:");
            }
            else{
                System.out.println("Sensor:" + this.sensorType + " nicht gefunden!!!");
                setSensorValue(Float.parseFloat("-99999.99"));
            }
        }
    }

    private void setSensorValue(Float newValue)
    {
        sensorVal = newValue;

        Md2AttributeSetTask var3 = null;
        try {
            var3 = new Md2AttributeSetTask("SensorDatenProvider", varName, new Md2Sensor(sensorVal));
            var3.execute();
        }catch (Md2WidgetNotCreatedException e){
            Md2TaskQueue.getInstance().addPendingTask(var3);
        }
    }

    public Float getSensorValue(){
        return sensorVal;
    }

}