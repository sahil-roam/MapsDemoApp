package com.local.mapsdemoapp

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.location.Location
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.localbroadcastmanager.content.LocalBroadcastManager

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.local.mapsdemoapp.databinding.ActivityMapsBinding

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private lateinit var startTracking:Button
    private lateinit var stopTracking:Button
    private var firstLocationRecorded = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        locationPermissionResultLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        startTracking = findViewById(R.id.start_tracking)
        stopTracking = findViewById(R.id.stop_tracking)

        startTracking.setOnClickListener {
            Log.e("TAG", "onCreate: start tracking")
            firstLocationRecorded = false
            startService(Intent(this, LocationService::class.java))
        }

        stopTracking.setOnClickListener {
            Log.e("TAG", "onCreate: stop tracking")
            firstLocationRecorded = true
            stopService(Intent(this, LocationService::class.java))
        }


    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        registerBroadcast()

    }

    val backgroundLocationPermissionResultLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ){isGranted ->
        if (isGranted){
            Toast.makeText(applicationContext, "Background Permission Granted", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(applicationContext, "Background Permission NOT Granted", Toast.LENGTH_SHORT).show()
        }
    }


    val locationPermissionResultLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ){isGranted ->
        if (isGranted){
            Toast.makeText(applicationContext, "Location Permission Granted", Toast.LENGTH_SHORT).show()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                backgroundLocationPermissionResultLauncher.launch(Manifest.permission.ACCESS_BACKGROUND_LOCATION)
            }
        } else {
            Toast.makeText(applicationContext, "Location Permission NOT Granted", Toast.LENGTH_SHORT).show()
        }
    }


    fun registerBroadcast(){

        val locationBroadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(p0: Context?, p1: Intent?) {
                p1 ?: return
                if (p1.action.equals("MY_LOCATION")){
                    val location = p1.getParcelableExtra<Location>("location_object")
                    mMap ?: return
                    val marker = LatLng(location!!.latitude, location.longitude)
                    mMap.addMarker(MarkerOptions().position(marker).title("Marker"))
                    if (!firstLocationRecorded){
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker, 18f))
                        firstLocationRecorded = true
                    }
                }
                }
            }
        LocalBroadcastManager.getInstance(applicationContext).registerReceiver(locationBroadcastReceiver, IntentFilter("MY_LOCATION"))
        }





}