package com.mfarhan08a.hangoutyuk.ui.maps

import android.content.Intent
import android.content.res.Resources
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions
import com.mfarhan08a.hangoutyuk.R
import com.mfarhan08a.hangoutyuk.data.Result
import com.mfarhan08a.hangoutyuk.data.model.PlaceItem
import com.mfarhan08a.hangoutyuk.databinding.ActivityMapsBinding
import com.mfarhan08a.hangoutyuk.ui.detail.DetailActivity
import com.mfarhan08a.hangoutyuk.util.ViewModelFactory

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private lateinit var location: Location
    private val boundsBuilder = LatLngBounds.Builder()

    private val mapsViewModel by viewModels<MapsViewModel> {
        ViewModelFactory.getInstance(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        location = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_PLACES, Location::class.java)!!
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_PLACES)!!
        }

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

        mapsViewModel.getToken().observe(this) { token ->
            if (token != null) {
                mapsViewModel.getPlaceRecomendation(token, location).observe(this) { result ->
                    when (result) {
                        is Result.Loading -> {
                            showLoading(true)
                        }
                        is Result.Success -> {
                            showLoading(false)
                            addPlaceMarkers(result.data.data)
                            mMap.setOnInfoWindowClickListener {
                                navigateToDetail(it.position, result.data.data)
                            }
                        }
                        is Result.Error -> {
                            showLoading(false)
                            Toast.makeText(this, getString(R.string.error_maps), Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
            }
        }

        setMapStyle()
    }

    private fun navigateToDetail(position: LatLng, data: List<PlaceItem>) {
        data.forEach {
            if (position.latitude == it.latitude && position.longitude == it.longitude) {
                val intent = Intent(this, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_PLACE_ID, it.id)
                startActivity(intent)
            }
        }
    }

    private fun addPlaceMarkers(places: List<PlaceItem>) {
        places.forEach {
            if (it.latitude != null && it.longitude != null) {
                val latLng = LatLng(it.latitude, it.longitude)
                mMap.addMarker(
                    MarkerOptions()
                        .position(latLng)
                        .title(it.name)
                        .snippet(it.category)
                )
                boundsBuilder.include(latLng)
            }
        }
        val bounds: LatLngBounds = boundsBuilder.build()
        mMap.animateCamera(
            CameraUpdateFactory.newLatLngBounds(
                bounds,
                resources.displayMetrics.widthPixels,
                resources.displayMetrics.heightPixels,
                300
            )
        )
    }

    private fun setMapStyle() {
        try {
            Log.e(TAG, "success?")
            val success =
                mMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                        this@MapsActivity,
                        R.raw.map_style
                    )
                )
            if (!success) {
                Log.e(TAG, getString(R.string.style_failed))
            }
        } catch (e: Resources.NotFoundException) {
            Log.e(TAG, getString(R.string.error_style), e)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    companion object {
        const val TAG = "MapsActivity"
        const val EXTRA_PLACES = "extra_places"
    }
}