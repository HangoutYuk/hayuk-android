package com.mfarhan08a.hangoutyuk.ui.choosemap

import android.content.Intent
import android.content.res.Resources
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.mfarhan08a.hangoutyuk.R
import com.mfarhan08a.hangoutyuk.databinding.ActivityChooseMapsBinding
import com.mfarhan08a.hangoutyuk.ui.maps.MapsActivity

class ChooseMapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityChooseMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityChooseMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        binding.topAppBar.setNavigationOnClickListener {
            finish()
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        mMap.uiSettings.apply {
            isZoomControlsEnabled = true
            isIndoorLevelPickerEnabled = true
            isCompassEnabled = true
            isMapToolbarEnabled = true
        }

        val userLocation = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_LOCATION, Location::class.java)!!
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_LOCATION)!!
        }

        val lng = LatLng(userLocation.latitude, userLocation.longitude)

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(lng, 10f))

        mMap.setOnMapClickListener {
            val selectedLocation = Location("provider")
            selectedLocation.latitude = it.latitude
            selectedLocation.longitude = it.longitude
            val locationIntent = Intent()
            locationIntent.putExtra(EXTRA_SELECTED_LOCATION, selectedLocation)
            setResult(RESULT_CODE, locationIntent)
            finish()
        }

        setMapStyle()
    }

    private fun setMapStyle() {
        try {
            Log.e(MapsActivity.TAG, "success")
            val success =
                mMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                        this,
                        R.raw.map_style
                    )
                )
            if (!success) {
                Log.e(MapsActivity.TAG, getString(R.string.style_failed))
            }
        } catch (e: Resources.NotFoundException) {
            Log.e(MapsActivity.TAG, getString(R.string.error_style), e)
        }
    }

    companion object {
        const val EXTRA_LOCATION = "extra_location"
        const val EXTRA_SELECTED_LOCATION = "extra_selected_location"
        const val RESULT_CODE = 110
    }
}