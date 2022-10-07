package com.local.mapsdemoapp

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
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

        //locationPermissionResultLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)

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

        //registerBroadcast()

        var options: PolylineOptions = PolylineOptions()
            .clickable(true)
            .add(
                LatLng(28.43381483, 77.06540338),
                LatLng(28.43393224, 77.06550934),
                LatLng(28.43404965, 77.06561529),
                LatLng(28.43416706, 77.06572125),
                LatLng(28.43427169, 77.06567774),
                LatLng(28.43436896, 77.06554806),
                LatLng(28.43446622, 77.06541837),
                LatLng(28.43456349, 77.06528868),
                LatLng(28.43465967, 77.06515796),
                LatLng(28.43476401, 77.06510401),
                LatLng(28.43487657, 77.06521657),
                LatLng(28.43498913, 77.06532913),
                LatLng(28.43510464, 77.06543775),
                LatLng(28.43522018, 77.06554633),
                LatLng(28.43533571, 77.06565492),
                LatLng(28.43545125, 77.06576351),
                LatLng(28.43556679, 77.06587209),
                LatLng(28.43568232, 77.06598068),
                LatLng(28.43579786, 77.06608927),
                LatLng(28.43591339, 77.06619785),
                LatLng(28.43602893, 77.06630644),
                LatLng(28.43614447, 77.06641503),
                LatLng(28.43626, 77.06652361),
                LatLng(28.43637411, 77.06663411),
                LatLng(28.43648667, 77.06674667),
                LatLng(28.43659923, 77.06685923),
                LatLng(28.43671178, 77.06697178),
                LatLng(28.43682434, 77.06708434),
                LatLng(28.43693895, 77.06719408),
                LatLng(28.43705637, 77.0673),
                LatLng(28.43717306, 77.06740697),
                LatLng(28.43729444, 77.06750671),
                LatLng(28.43741896, 77.06760159),
                LatLng(28.43754668, 77.06769078),
                LatLng(28.43767471, 77.06777941),
                LatLng(28.43780142, 77.06787047),
                LatLng(28.43792274, 77.06796996),
                LatLng(28.4380379, 77.06807906),
                LatLng(28.43815393, 77.06818697),
                LatLng(28.43827016, 77.06829459),
                LatLng(28.43838514, 77.06840392),
                LatLng(28.43849878, 77.06851506),
                LatLng(28.43861243, 77.0686262),
                LatLng(28.43872608, 77.06873734),
                LatLng(28.43883972, 77.06884848),
                LatLng(28.43895337, 77.06895962),
                LatLng(28.43906702, 77.06907076),
                LatLng(28.43918066, 77.0691819),
                LatLng(28.43929431, 77.06929304),
                LatLng(28.43940795, 77.06940418),
                LatLng(28.4395216, 77.06951532),
                LatLng(28.43963525, 77.06962646),
                LatLng(28.43974888, 77.06973762),
                LatLng(28.43986249, 77.0698488),
                LatLng(28.43997611, 77.06995998),
                LatLng(28.44008972, 77.07007116),
                LatLng(28.44020334, 77.07018234),
                LatLng(28.44031696, 77.07029352),
                LatLng(28.44043057, 77.0704047),
                LatLng(28.44054419, 77.07051588),
                LatLng(28.4406578, 77.07062706),
                LatLng(28.44077142, 77.07073825),
                LatLng(28.44088504, 77.07084943),
                LatLng(28.44099865, 77.07096061),
                LatLng(28.44111236, 77.07107166),
                LatLng(28.44122646, 77.0711822),
                LatLng(28.44134057, 77.07129274),
                LatLng(28.44145467, 77.07140327),
                LatLng(28.44156877, 77.07151381),
                LatLng(28.44168287, 77.07162434),
                LatLng(28.44179676, 77.07173516),
                LatLng(28.4419105, 77.07184618),
                LatLng(28.44202424, 77.0719572),
                LatLng(28.44213799, 77.07206821),
                LatLng(28.44225173, 77.07217923),
                LatLng(28.44236547, 77.07229025),
                LatLng(28.44247921, 77.07240127),
                LatLng(28.44259295, 77.07251228),
                LatLng(28.44270669, 77.0726233),
                LatLng(28.44282043, 77.07273432),
                LatLng(28.44293418, 77.07284533),
                LatLng(28.44304792, 77.07295635),
                LatLng(28.44316166, 77.07306737),
                LatLng(28.4432754, 77.07317839),
                LatLng(28.44338914, 77.0732894),
                LatLng(28.44350303, 77.07340023),
                LatLng(28.44361693, 77.07351104),
                LatLng(28.44373083, 77.07362184),
                LatLng(28.44384473, 77.07373265),
                LatLng(28.44395864, 77.07384345),
                LatLng(28.44407254, 77.07395426),
                LatLng(28.44418644, 77.07406507),
                LatLng(28.44430034, 77.07417587),
                LatLng(28.44441424, 77.07428668),
                LatLng(28.44452814, 77.07439749),
                LatLng(28.44464204, 77.07450829),
                LatLng(28.44475595, 77.0746191),
                LatLng(28.44486985, 77.07472991),
                LatLng(28.44498375, 77.07484071),
                LatLng(28.44509765, 77.07495152),
                LatLng(28.44521155, 77.07506233),
                LatLng(28.44532445, 77.07517445),
                LatLng(28.44543701, 77.07528701),
                LatLng(28.44554958, 77.07539958),
                LatLng(28.44566214, 77.07551214),
                LatLng(28.4457747, 77.0756247),
                LatLng(28.44588028, 77.07574535),
                LatLng(28.4459568, 77.07588882),
                LatLng(28.44598266, 77.07605655),
                LatLng(28.44597651, 77.07622532),
                LatLng(28.44595328, 77.07639373),
                LatLng(28.44593636, 77.0765629),
                LatLng(28.44593534, 77.07673273),
                LatLng(28.44597004, 77.07689676),
                LatLng(28.44603571, 77.07705),
                LatLng(28.44610161, 77.0772031),
                LatLng(28.44617774, 77.07734993),
                LatLng(28.44625466, 77.07749623),
                LatLng(28.44633506, 77.0776401),
                LatLng(28.44641545, 77.07778397),
                LatLng(28.44649645, 77.0779274),
                LatLng(28.44657759, 77.07807074),
                LatLng(28.44665872, 77.07821408),
                LatLng(28.44674008, 77.07835724),
                LatLng(28.4468231, 77.07849917),
                LatLng(28.44690612, 77.07864111),
                LatLng(28.44698914, 77.07878304),
                LatLng(28.4470836, 77.0789136),
                LatLng(28.44720145, 77.07901195),
                LatLng(28.44735018, 77.0790283),
                LatLng(28.44749849, 77.07900359),
                LatLng(28.44764476, 77.07896673),
                LatLng(28.44776659, 77.07887163),
                LatLng(28.44788338, 77.07876478),
                LatLng(28.44800017, 77.07865793),
                LatLng(28.44811696, 77.07855108),
                LatLng(28.44824098, 77.0784556),
                LatLng(28.44836699, 77.07836329),
                LatLng(28.44849352, 77.0782719),
                LatLng(28.44862109, 77.07818241),
                LatLng(28.44874882, 77.07809321),
                LatLng(28.44887654, 77.078004),
                LatLng(28.44900427, 77.0779148),
                LatLng(28.449132, 77.07782559),
                LatLng(28.44925883, 77.07773475),
                LatLng(28.44938561, 77.0776438),
                LatLng(28.44951238, 77.07755286),
                LatLng(28.44963697, 77.07745831),
                LatLng(28.44976243, 77.0773653),
                LatLng(28.44989007, 77.07727595),
                LatLng(28.45001762, 77.07718641),
                LatLng(28.450145, 77.07709655),
                LatLng(28.45027237, 77.0770067),
                LatLng(28.45039975, 77.07691684),
                LatLng(28.45052712, 77.07682699),
                LatLng(28.4506545, 77.07673713),
                LatLng(28.45078188, 77.07664728),
                LatLng(28.45090925, 77.07655743),
                LatLng(28.45103663, 77.07646757),
                LatLng(28.45116401, 77.07637772),
                LatLng(28.45129101, 77.07628718),
                LatLng(28.45141685, 77.07619458),
                LatLng(28.4515427, 77.07610198),
                LatLng(28.45166854, 77.07600937),
                LatLng(28.45179445, 77.07591688),
                LatLng(28.4519221, 77.07582753),
                LatLng(28.45204975, 77.07573818),
                LatLng(28.4521774, 77.07564882),
                LatLng(28.45230475, 77.07555894),
                LatLng(28.45242988, 77.07546509),
                LatLng(28.45255501, 77.07537124),
                LatLng(28.45268115, 77.07527923),
                LatLng(28.45281026, 77.07519266),
                LatLng(28.45293712, 77.07510185),
                LatLng(28.45306397, 77.07501105),
                LatLng(28.45319083, 77.07492025),
                LatLng(28.45331769, 77.07482944),
                LatLng(28.45344454, 77.07473864),
                LatLng(28.4535714, 77.07464784),
                LatLng(28.45369826, 77.07455704),
                LatLng(28.45382526, 77.0744665),
                LatLng(28.45395457, 77.07438029),
                LatLng(28.45408388, 77.07429408),
                LatLng(28.45421731, 77.07421934),
                LatLng(28.45436397, 77.0741878),
                LatLng(28.45451371, 77.07418031),
                LatLng(28.45466355, 77.07417619),
                LatLng(28.4548134, 77.07417221),
                LatLng(28.45496324, 77.07416823),
                LatLng(28.45511309, 77.07416425),
                LatLng(28.45526293, 77.07416028),
                LatLng(28.45541278, 77.0741563),
                LatLng(28.45556263, 77.07415232),
                LatLng(28.45571246, 77.07414777),
                LatLng(28.45586227, 77.07414242),
                LatLng(28.45601209, 77.07413707),
                LatLng(28.4561619, 77.07413172),
                LatLng(28.45631171, 77.07412632),
                LatLng(28.45646152, 77.07412091),
                LatLng(28.45661133, 77.07411549),
                LatLng(28.45676114, 77.07411008),
                LatLng(28.45691096, 77.07410466),
                LatLng(28.45706075, 77.07409883),
                LatLng(28.45721045, 77.07409035),
                LatLng(28.45736015, 77.07408188),
                LatLng(28.45750986, 77.0740734),
                LatLng(28.45765962, 77.07406655),
                LatLng(28.45780942, 77.07406079),
                LatLng(28.45795922, 77.07405503),
                LatLng(28.45810895, 77.07404794),
                LatLng(28.45825815, 77.07403172),
                LatLng(28.45840736, 77.0740155),
                LatLng(28.4585566, 77.074),
                LatLng(28.45870648, 77.074),
                LatLng(28.45885625, 77.07399708),
                LatLng(28.45900577, 77.07398627),
                LatLng(28.45915552, 77.07398066),
                LatLng(28.45930458, 77.07399855),
                LatLng(28.45945393, 77.07400923),
                LatLng(28.45960381, 77.07400709),
                LatLng(28.45975368, 77.07400495),
                LatLng(28.45990356, 77.07400281),
                LatLng(28.46005343, 77.07400067),
                LatLng(28.46020325, 77.07399587),
                LatLng(28.46035304, 77.07398979),
                LatLng(28.46050265, 77.07397947),
                LatLng(28.46065227, 77.07396931),
                LatLng(28.46080198, 77.07396083),
                LatLng(28.46095168, 77.07395236),
                LatLng(28.46110138, 77.07394388),
                LatLng(28.46125052, 77.07392822),
                LatLng(28.46139918, 77.07390646),
                LatLng(28.46154784, 77.07388471),
                LatLng(28.46169649, 77.07386283),
                LatLng(28.46184513, 77.07384093),
                LatLng(28.46199378, 77.07381902),
                LatLng(28.46214242, 77.07379712),
                LatLng(28.46229107, 77.07377521),
                LatLng(28.46243971, 77.07375331),
                LatLng(28.4625885, 77.07373274),
                LatLng(28.4627375, 77.07371424),
                LatLng(28.4628865, 77.07369574),
                LatLng(28.4630355, 77.07367725),
                LatLng(28.46318451, 77.07365875),
                LatLng(28.46333351, 77.07364025),
                LatLng(28.46348251, 77.07362176),
                LatLng(28.46363151, 77.07360326),
                LatLng(28.46378052, 77.07358476),
                LatLng(28.46392952, 77.07356627),
                LatLng(28.46407856, 77.07354817),
                LatLng(28.46422762, 77.07353029),
                LatLng(28.46437649, 77.0735105),
                LatLng(28.46452447, 77.07348425),
                LatLng(28.46467295, 77.07346101),
                LatLng(28.46482167, 77.07343976),
                LatLng(28.46497035, 77.07341814),
                LatLng(28.46511899, 77.07339619),
                LatLng(28.46526763, 77.07337424),
                LatLng(28.46541627, 77.0733523),
                LatLng(28.46556491, 77.07333035),
                LatLng(28.46571355, 77.0733084),
                LatLng(28.46586219, 77.07328646),
                LatLng(28.46601083, 77.07326451),
                LatLng(28.46615947, 77.07324256),
                LatLng(28.46630811, 77.07322061),
                LatLng(28.46645675, 77.07319867),
                LatLng(28.46660539, 77.07317672),
                LatLng(28.46675403, 77.07315477),
                LatLng(28.46690267, 77.07313283),
                LatLng(28.46705131, 77.07311088),
                LatLng(28.46719995, 77.07308893),
                LatLng(28.46734859, 77.07306699),
                LatLng(28.46749723, 77.07304504),
                LatLng(28.46764587, 77.07302309),
                LatLng(28.46779451, 77.07300115),
                LatLng(28.4679432, 77.07297966),
                LatLng(28.46809194, 77.07295864),
                LatLng(28.46824069, 77.07293762),
                LatLng(28.46838943, 77.0729166),
                LatLng(28.46853818, 77.07289558),
                LatLng(28.46868692, 77.07287457),
                LatLng(28.46883566, 77.07285355),
                LatLng(28.46898441, 77.07283253),
                LatLng(28.46913315, 77.07281151),
                LatLng(28.46928189, 77.07279049),
                LatLng(28.46943064, 77.07276948),
                LatLng(28.46957938, 77.07274846),
                LatLng(28.46972813, 77.07272744)
            )

        mMap.addPolyline(options)

        val start = LatLng(28.43381483, 77.06540338)
        val end = LatLng(28.46972813, 77.07272744)
        mMap.addMarker(MarkerOptions().position(start).title("Start"))
        mMap.addMarker(MarkerOptions().position(end).title("End"))

        val builder = LatLngBounds.Builder()

        //the include method will calculate the min and max bound.

        //the include method will calculate the min and max bound.
        builder.include(start)
        builder.include(end)

        val bounds = builder.build()

        val zoomWidth = resources.displayMetrics.widthPixels
        val zoomHeight = resources.displayMetrics.heightPixels
        val zoomPadding = 0

        mMap.setPadding(0, (zoomHeight * 0.15).toInt(), 0, (zoomHeight * 0.6).toInt())

        Handler().postDelayed( object: Runnable{
            override fun run() {
                mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, zoomWidth, zoomHeight, zoomPadding))
            }
        }, 2000)


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