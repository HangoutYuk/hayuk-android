package com.mfarhan08a.hangoutyuk.ui.home

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.mfarhan08a.hangoutyuk.R
import com.mfarhan08a.hangoutyuk.data.Result
import com.mfarhan08a.hangoutyuk.data.model.DataUser
import com.mfarhan08a.hangoutyuk.data.model.PlaceItem
import com.mfarhan08a.hangoutyuk.databinding.FragmentHomeBinding
import com.mfarhan08a.hangoutyuk.ui.adapter.PlaceAdapter
import com.mfarhan08a.hangoutyuk.ui.choosemap.ChooseMapsActivity
import com.mfarhan08a.hangoutyuk.ui.detail.DetailActivity
import com.mfarhan08a.hangoutyuk.ui.login.LoginActivity
import com.mfarhan08a.hangoutyuk.ui.maps.MapsActivity
import com.mfarhan08a.hangoutyuk.ui.onboarding.OnboardingActivity
import com.mfarhan08a.hangoutyuk.ui.profile.ProfileActivity
import com.mfarhan08a.hangoutyuk.util.ViewModelFactory

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private lateinit var tkn: String
    private var fromChooseMaps: Boolean = false


    private val homeViewModel by viewModels<HomeViewModel> {
        ViewModelFactory.getInstance(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        Log.d(TAG, "userloc: ${location.toString()}")

        val layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.recyclerViewPlace.layoutManager = layoutManager

        try {
            homeViewModel.getToken().observe(viewLifecycleOwner) { token ->
                if (token != null) {
                    tkn = token

                    if (!fromChooseMaps && places.isNullOrEmpty()) {
                        getMyLastLocation(token)
                    } else {
                        try {
                            setPlacesData(places!!, location!!)
                        } catch (e: Exception) {
                            Log.d(TAG, e.toString())
                        }
                    }

                    try {
                        binding.fabMylocation.setOnClickListener {
                            fromChooseMaps = false
                            getMyLastLocation(token)
                            Toast.makeText(
                                requireContext(),
                                getString(R.string.get_recommendation_mylocation),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } catch (e: Exception) {
                        Log.d(TAG, e.toString())
                    }
                    homeViewModel.getId().observe(viewLifecycleOwner) { id ->
                        homeViewModel.getUserById(token, id!!).observe(viewLifecycleOwner) {
                            when (it) {
                                is Result.Loading -> {
                                    Log.d(TAG, "loading data user..")
                                }
                                is Result.Success -> {
                                    try {
                                        dataUser = it.data.data
                                        binding.textViewName.text = it.data.data.name
                                        if (it.data.data.photoUrl == null) {
                                            binding.ivUserPhoto.setImageResource(R.drawable.blank_picture)
                                        } else {
                                            Glide.with(requireActivity())
                                                .load(it.data.data.photoUrl)
                                                .into(binding.ivUserPhoto)
                                        }
                                    } catch (e: Exception) {
                                        Log.d(TAG, e.toString())
                                    }
                                }
                                is Result.Error -> {
                                    Log.d(TAG, "e: ${it.error}")
                                    if (it.error.trim() == "HTTP 400") {
                                        logout()
                                    }
                                }
                            }
                        }
                        try {
                            binding.cardView.setOnClickListener {
                                navigateToProfile()
                            }
                        } catch (e: Exception) {
                            Log.d(TAG, e.toString())
                        }
                    }
                } else {
                    logout()
                }
            }
        } catch (e: Exception) {
            Log.d(TAG, e.toString())
        }

        binding.apply {
            btnLogout.setOnClickListener {
                logout()
            }
            btnInfo.setOnClickListener {
                navigateToOnboarding()
            }
            fabMaps.setOnClickListener {
                navigateToMaps(location!!)
            }
            fabChooseLocation.setOnClickListener {
                fromChooseMaps = true
                navigateToChooseMaps()
            }
        }

    }

    private val resultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == ChooseMapsActivity.RESULT_CODE && result.data != null) {
            val location =
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    result.data?.getParcelableExtra(
                        ChooseMapsActivity.EXTRA_SELECTED_LOCATION,
                        Location::class.java
                    )
                } else {
                    @Suppress("DEPRECATION")
                    result.data?.getParcelableExtra(ChooseMapsActivity.EXTRA_SELECTED_LOCATION)
                }

            Log.d(TAG, "chosen loc: ${location.toString()}")

            homeViewModel.getPlaceRecommendation(tkn, location!!).observe(viewLifecycleOwner) {
                when (it) {
                    is Result.Loading -> {
                        showLoading(true)
                        Toast.makeText(
                            requireContext(),
                            getString(R.string.get_recommendation_choose),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    is Result.Success -> {
                        places = it.data.data
                        setPlacesData(it.data.data, HomeFragment.location!!)
                        showLoading(false)
                        fromChooseMaps = false
                    }
                    is Result.Error -> {
                        showLoading(false)
                        Toast.makeText(
                            requireContext(),
                            "getplace error: ${it.error}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        } else {
            Toast.makeText(
                requireContext(),
                getString(R.string.no_location_chosen),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun navigateToChooseMaps() {
        val intent = Intent(activity, ChooseMapsActivity::class.java)
        intent.putExtra(ChooseMapsActivity.EXTRA_LOCATION, location)
        resultLauncher.launch(intent)
    }

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            when {
                permissions[Manifest.permission.ACCESS_FINE_LOCATION] ?: false -> {
                    getMyLastLocation(tkn)
                }
                permissions[Manifest.permission.ACCESS_COARSE_LOCATION] ?: false -> {
                    getMyLastLocation(tkn)
                }
                else -> {
                    Log.d(TAG, "can't get permission")
                }
            }
        }

    private fun checkPermission(permission: String): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(),
            permission
        ) == PackageManager.PERMISSION_GRANTED
    }

    @SuppressLint("MissingPermission")
    fun getMyLastLocation(token: String) {
        if (checkPermission(Manifest.permission.ACCESS_FINE_LOCATION) &&
            checkPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
        ) {
            showLoading(true)
            fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
                if (location != null) {
                    homeViewModel.getPlaceRecommendation(token, location)
                        .observe(viewLifecycleOwner) {
                            when (it) {
                                is Result.Loading -> {
                                    showLoading(true)
                                }
                                is Result.Success -> {
                                    try {
                                        places = it.data.data
                                        setPlacesData(it.data.data, location)
                                        showLoading(false)
                                    } catch (e: Exception) {
                                        Log.d(TAG, e.toString())
                                    }
                                }
                                is Result.Error -> {
                                    showLoading(false)
                                    Toast.makeText(
                                        requireContext(),
                                        "getplace error: ${it.error}",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    if (it.error == "HTTP 400") {
                                        logout()
                                    }
                                }
                            }
                        }
                    HomeFragment.location = location
                } else {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.location_not_found),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        } else {
            requestPermissionLauncher.launch(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            )
        }
    }

    private fun logout() {
        homeViewModel.clearToken()
        val intent = Intent(requireContext(), LoginActivity::class.java)
        startActivity(intent)
        activity?.finish()
    }

    private fun navigateToProfile() {
        val intent = Intent(activity, ProfileActivity::class.java)
        intent.putExtra(ProfileActivity.EXTRA_USER, dataUser!!)
        startActivity(intent)
    }

    private fun setPlacesData(data: List<PlaceItem>, location: Location) {
        if (isAdded) {
            val adapter = PlaceAdapter(data, location)
            adapter.setOnItemClickCallBack(object : PlaceAdapter.OnItemClickCallback {
                override fun onItemClicked(place: PlaceItem) {
                    Log.d(TAG, "place: $place")
                    val intent = Intent(requireContext(), DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_PLACE_ID, place.id)
                    intent.putExtra(DetailActivity.EXTRA_USER_ID, dataUser?.id)
                    startActivity(intent)
                }
            })
            binding.recyclerViewPlace.adapter = adapter
        }
    }

    private fun showLoading(isLoading: Boolean) {
        try {
            if (isAdded) {
                binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
            }
        } catch (e: Exception) {
            Log.d(TAG, e.toString())
        }
    }

    private fun navigateToMaps(location: Location) {
        val intent = Intent(activity, MapsActivity::class.java)
        intent.putExtra(MapsActivity.EXTRA_PLACES, location)
        startActivity(intent)
    }

    private fun navigateToOnboarding() {
        val intent = Intent(activity, OnboardingActivity::class.java)
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private val TAG = HomeFragment::class.java.simpleName
        var location: Location? = null
        var places: List<PlaceItem>? = null
        var dataUser: DataUser? = null
    }
}