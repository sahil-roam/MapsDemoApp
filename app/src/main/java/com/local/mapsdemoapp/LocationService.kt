package com.local.mapsdemoapp

import android.Manifest
import android.app.Service
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.IBinder
import android.os.Looper
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.android.gms.location.*

class LocationService: Service() {

    lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }

    override fun onCreate() {
        super.onCreate()

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
            startForeground(NotificationsHelper.NOTIFICATION_ID, NotificationsHelper.showNotification(this))
        }

        registerFusedLocationClient()
    }

    fun registerFusedLocationClient(){
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        val locationRequest: LocationRequest = LocationRequest.create().apply {
            interval = 1000
            fastestInterval = 1000
            smallestDisplacement = 0f
            priority = Priority.PRIORITY_HIGH_ACCURACY
        }

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper())

    }

    val locationCallback = object : LocationCallback() {

        override fun onLocationResult(p0: LocationResult) {
            super.onLocationResult(p0)

            p0 ?: return

            for (location in p0.locations){
                //send broadcast
                Log.e("TAG", "onLocationResult: ${location.latitude} - ${location.longitude}")
                val intent = Intent("MY_LOCATION")
                intent.putExtra("location_object", location)
                LocalBroadcastManager.getInstance(applicationContext).sendBroadcast(intent)
            }
        }

    }

    fun stopTracking(){

        fusedLocationProviderClient ?: return

        fusedLocationProviderClient.removeLocationUpdates(locationCallback)
    }

    override fun onDestroy() {
        super.onDestroy()
        stopTracking()
    }

}