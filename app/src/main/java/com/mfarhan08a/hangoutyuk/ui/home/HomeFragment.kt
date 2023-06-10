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
import androidx.lifecycle.lifecycleScope
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
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private var dataUser: DataUser? = null
    private lateinit var tkn: String

    private val homeViewModel by viewModels<HomeViewModel> {
        ViewModelFactory.getInstance(requireContext())
    }

    companion object {
        private val TAG = HomeFragment::class.java.simpleName
        var location: Location? = null
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

        val layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.recyclerViewPlace.layoutManager = layoutManager

        Log.d(TAG, location.toString())

        homeViewModel.getToken().observe(requireActivity()) { token ->
            if (token != null) {
                tkn = token
                showLoading(true)
                getMyLastLocation(token)
                binding.fabMylocation.setOnClickListener {
                    getMyLastLocation(token)
                    Toast.makeText(
                        requireContext(),
                        "getting recomendation for your location..",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                homeViewModel.getId().observe(requireActivity()) { id ->
                    homeViewModel.getUserById(token, id!!).observe(requireActivity()) {
                        when (it) {
                            is Result.Loading -> {
                            }
                            is Result.Success -> {
                                dataUser = it.data.data
                                binding.textViewName.text = it.data.data.name
                                if (it.data.data.photoUrl == null) {
                                    binding.ivUserPhoto.setImageResource(R.drawable.blank_picture)
                                } else {
                                    Glide.with(requireActivity())
                                        .load(it.data.data.photoUrl)
                                        .into(binding.ivUserPhoto)
                                }
                            }
                            is Result.Error -> {
                                Log.d(TAG, it.error)
                                Toast.makeText(
                                    requireContext(),
                                    "getplace e: ${it.error}",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                    binding.cardView.setOnClickListener {
                        navigateToProfile()
                    }
                }
            } else {
                logout()
            }
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

            homeViewModel.getPlaceRecomendation(tkn, location!!).observe(this) {
                when (it) {
                    is Result.Loading -> {
                        showLoading(true)
                        Toast.makeText(
                            requireContext(),
                            "Getting Your Choosen Location",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    is Result.Success -> {
                        setPlacesData(it.data.data, location)
                        showLoading(false)
                    }
                    is Result.Error -> {
                        showLoading(false)
                        Toast.makeText(
                            requireContext(),
                            "getplace e: ${it.error}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        } else {
            Toast.makeText(
                requireContext(),
                "cant choose location",
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
                    lifecycleScope.launch {
                        getMyLastLocation(tkn)
                    }
                }
                permissions[Manifest.permission.ACCESS_COARSE_LOCATION] ?: false -> {
                    lifecycleScope.launch {
                        getMyLastLocation(tkn)
                    }
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
                    homeViewModel.getPlaceRecomendation(token, location)
                        .observe(requireActivity()) {
                            when (it) {
                                is Result.Loading -> {
                                    showLoading(true)
                                }
                                is Result.Success -> {
                                    setPlacesData(it.data.data, location)
                                    showLoading(false)
                                }
                                is Result.Error -> {
                                    showLoading(false)
                                    Toast.makeText(
                                        requireContext(),
                                        "getplace e: ${it.error}",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        }
                    HomeFragment.location = location
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Location is not found. Try Again",
                        Toast.LENGTH_SHORT
                    ).show()
                    getMyLastLocation(token)
                    showLoading(true)
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
        val adapter = PlaceAdapter(data, location)

        adapter.setOnItemClickCallBack(object : PlaceAdapter.OnItemClickCallback {
            override fun onItemClicked(place: PlaceItem) {
                Log.d(TAG, "place: $place")
                val intent = Intent(requireContext(), DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_PLACE_ID, place.id)
                startActivity(intent)
            }
        })
        binding.recyclerViewPlace.adapter = adapter
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
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
}