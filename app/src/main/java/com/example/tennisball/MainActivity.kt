package com.example.tennisball

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var mSensorManager : SensorManager
    private var mAccelerometer : Sensor ?= null

    var XPosition = 0.0
    var YPosition = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mSensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        setContentView(R.layout.activity_main)

    }


    /**
     * Runs every time a sensor has been updated
     */
    override fun onSensorChanged(event: SensorEvent?) {
        val Ball = findViewById<ImageView>(R.id.Ball)

        if (event?.sensor?.type == Sensor.TYPE_ACCELEROMETER) {
            XPosition -= event.values.get(0)
            YPosition -= event.values.get(1)

            Ball.setX(XPosition.toFloat())
            Ball.setY(YPosition.toFloat())
        }
    }

    /**
     * Needs to be implemented to use the SensorEventListener, in a real program this should have a purpose
     */
    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        return
    }

    override fun onResume() {
        super.onResume()
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        super.onPause()
        mSensorManager.unregisterListener(this)
    }
}