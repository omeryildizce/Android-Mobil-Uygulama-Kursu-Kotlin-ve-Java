package com.omeryildizce.kotlinmaps.view

import android.Manifest
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.room.Room
import androidx.room.RoomDatabase

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.snackbar.Snackbar
import com.omeryildizce.kotlinmaps.R
import com.omeryildizce.kotlinmaps.databinding.ActivityMapsBinding
import com.omeryildizce.kotlinmaps.model.Place
import com.omeryildizce.kotlinmaps.roomdb.PlaceDao
import com.omeryildizce.kotlinmaps.roomdb.PlaceDatabase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMapLongClickListener {
    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private lateinit var locationManager: LocationManager
    private lateinit var locationListener: LocationListener
    private lateinit var permisonLauncher: ActivityResultLauncher<String>
    private lateinit var sharedPreferences: SharedPreferences
    private var trackBoolean: Boolean? = null
    private var selectedLatitude: Double? = null
    private var selectedLongtitude: Double? = null
    private lateinit var database: PlaceDatabase
    private lateinit var placeDao: PlaceDao
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    var placeFromMain : Place? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        registerLauncher()
        sharedPreferences = this.getSharedPreferences("com.omeryildizce.kotlinmaps", MODE_PRIVATE)
        trackBoolean = false
        selectedLatitude = 0.0
        selectedLongtitude = 0.0

        database = Room.databaseBuilder(applicationContext, PlaceDatabase::class.java, "Places")
            // .allowMainThreadQueries( )
            .build()
        placeDao = database.placeDao()

        binding.saveButton.isEnabled = false
        saveLocation()
        deleteLocation()
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.setOnMapLongClickListener(this)

        var info = intent.getStringExtra("info")
        if (info != null) {
            if (info == "new") {
                binding.saveButton.visibility = View.VISIBLE
                binding.deleteButton.visibility = View.GONE

                // casting
                locationManager = this.getSystemService(LOCATION_SERVICE) as LocationManager
                locationListener = object : LocationListener {
                    override fun onLocationChanged(location: Location) {
                        trackBoolean = sharedPreferences.getBoolean("trackBoolean", false)
                        if (!trackBoolean!!) {
                            mMap.clear()
                            val userLocation = LatLng(location.latitude, location.longitude)
                            mMap.addMarker(
                                MarkerOptions().position(userLocation).title("Konumunuz")
                            )
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 15f))
                            sharedPreferences.edit().putBoolean("trackBoolean", true).apply()
                        }


                    }
                }


                if (ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(
                            this,
                            Manifest.permission.ACCESS_FINE_LOCATION
                        )
                    ) {
                        Snackbar.make(
                            binding.root,
                            "Permission needed for access location",
                            Snackbar.LENGTH_INDEFINITE
                        )
                            .setAction("Give permission") {
                                permisonLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
                            }.show()

                    } else {
                        permisonLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
                    }
                } else {
                    locationManager.requestLocationUpdates(
                        LocationManager.GPS_PROVIDER,
                        10_000,
                        10f,
                        locationListener
                    )
                    val lastLocation =
                        locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                    if (lastLocation != null) {
                        val lastUserLocation = LatLng(lastLocation.latitude, lastLocation.longitude)
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lastUserLocation, 15f))

                    }
                    mMap.isMyLocationEnabled = true
                }
            } else {
                binding.saveButton.visibility = View.GONE
                binding.deleteButton.visibility = View.VISIBLE
                mMap.clear()
                placeFromMain = intent.getSerializableExtra("selectedPlace") as Place?
                placeFromMain?.let {place->
                    val latLng = LatLng(place.latitude, place.longitude)
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,15f))
                    mMap.addMarker(MarkerOptions().position(latLng).title(place.name))
                    binding.locationName.setText(place.name)

                }
            }
        }

        // golbasi()
    }

    private fun registerLauncher() {
        permisonLauncher =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) { result ->
                if (result) {
                    if (ActivityCompat.checkSelfPermission(
                            this,
                            Manifest.permission.ACCESS_FINE_LOCATION
                        ) == PackageManager.PERMISSION_GRANTED
                    ) {
                        locationManager.requestLocationUpdates(
                            LocationManager.GPS_PROVIDER,
                            10_000,
                            10f,
                            locationListener
                        )
                        val lastLocation =
                            locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                        if (lastLocation != null) {
                            val lastUserLocation =
                                LatLng(lastLocation.latitude, lastLocation.longitude)
                            mMap.moveCamera(
                                CameraUpdateFactory.newLatLngZoom(
                                    lastUserLocation,
                                    15f
                                )
                            )

                        }
                    }

                } else {
                    Toast.makeText(this, "Permission needed", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun golbasi() {
        // 39.788215, 32.801177
        val golbasi = LatLng(39.788215, 32.801177)
        mMap.addMarker(MarkerOptions().position(golbasi).title("Gölbaşı"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(golbasi, 15f))
    }

    override fun onMapLongClick(p0: LatLng) {
        mMap.clear()
        mMap.addMarker(MarkerOptions().position(p0))

        selectedLatitude = p0.latitude
        selectedLongtitude = p0.longitude
        binding.saveButton.isEnabled = true

    }


    private fun saveLocation() {
        binding.saveButton.setOnClickListener {
            // Main Thread UI, Default -> cpu, IO Thread internet database,

            if (selectedLatitude != null && selectedLongtitude != null) {
                val locationName = binding.locationName.text.toString()
                val place = Place(locationName, selectedLatitude!!, selectedLongtitude!!)
                compositeDisposable.add(
                    placeDao.insert(place)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(this::handleResponse)
                )
            }

        }
    }

    private fun handleResponse() {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

    private fun deleteLocation() {
        binding.deleteButton.setOnClickListener {
            placeFromMain?.let {

             compositeDisposable.add(
                 placeDao.delete(it)
                     .subscribeOn(Schedulers.io())
                     .observeOn(AndroidSchedulers.mainThread())
                     .subscribe(this::handleResponse)
             )

            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}