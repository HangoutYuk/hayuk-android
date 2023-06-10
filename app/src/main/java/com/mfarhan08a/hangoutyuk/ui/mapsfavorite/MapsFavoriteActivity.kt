package com.mfarhan08a.hangoutyuk.ui.mapsfavorite

import android.content.Intent
import android.content.res.Resources
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
import com.mfarhan08a.hangoutyuk.data.local.entity.FavoriteEntity
import com.mfarhan08a.hangoutyuk.databinding.ActivityMapsFavoriteBinding
import com.mfarhan08a.hangoutyuk.ui.detail.DetailActivity
import com.mfarhan08a.hangoutyuk.ui.maps.MapsActivity
import com.mfarhan08a.hangoutyuk.ui.maps.MapsViewModel
import com.mfarhan08a.hangoutyuk.util.ViewModelFactory

class MapsFavoriteActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsFavoriteBinding
    private val boundsBuilder = LatLngBounds.Builder()

    private val mapsViewModel by viewModels<MapsViewModel> {
        ViewModelFactory.getInstance(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsFavoriteBinding.inflate(layoutInflater)
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

        mapsViewModel.getFavoritePlaces().observe(this) { data ->
            if (data != null) {
                showLoading(true)
                addPlaceMarkers(data)
                mMap.setOnInfoWindowClickListener {
                    navigateToDetail(it.position, data)
                }
                showLoading(false)
            } else {
                Toast.makeText(this, getString(R.string.empty_favorite), Toast.LENGTH_SHORT).show()
            }
        }

        setMapStyle()
    }

    private fun navigateToDetail(position: LatLng, data: List<FavoriteEntity>) {
        data.forEach {
            if (position.latitude == it.latitude && position.longitude == it.longitude) {
                val intent = Intent(this, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_PLACE_ID, it.id)
                startActivity(intent)
            }
        }
    }

    private fun addPlaceMarkers(places: List<FavoriteEntity>) {
        places.forEach {
            val latLng = LatLng(it.latitude, it.longitude)
            mMap.addMarker(
                MarkerOptions()
                    .position(latLng)
                    .title(it.name)
                    .snippet(it.category)
            )
            boundsBuilder.include(latLng)
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

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}