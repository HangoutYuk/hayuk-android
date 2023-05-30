package com.mfarhan08a.hangoutyuk.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mfarhan08a.hangoutyuk.databinding.FragmentHomeBinding
import com.mfarhan08a.hangoutyuk.ui.profile.ProfileActivity
import com.mfarhan08a.hangoutyuk.data.Result
import com.mfarhan08a.hangoutyuk.data.model.Place
import com.mfarhan08a.hangoutyuk.ui.adapter.PlaceAdapter
import com.mfarhan08a.hangoutyuk.ui.detail.DetailActivity
import com.mfarhan08a.hangoutyuk.ui.login.LoginActivity
import com.mfarhan08a.hangoutyuk.util.ViewModelFactory

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

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

        val layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.recyclerViewPlace.layoutManager = layoutManager

        homeViewModel.getToken().observe(requireActivity()) { token ->
            if (token != null) {
//                Toast.makeText(requireContext(), token, Toast.LENGTH_SHORT).show()
            }
        }

        homeViewModel.getAllStories().observe(requireActivity()) {
            when (it) {
                is Result.Success -> {
                    setPlacesData(it.data)
                    showLoading(false)
                }
                is Result.Error -> {
                    showLoading(false)
                }
                is Result.Loading -> {
                    showLoading(true)
                }
            }
        }

        binding.fabMaps.setOnClickListener {
            navigateToMaps()
        }
        binding.cardView.setOnClickListener {
            navigateToProfile()
        }
        binding.btnLogout.setOnClickListener {
            logout()
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
        startActivity(intent)
    }

    private fun setPlacesData(data: List<Place>) {
        val adapter = PlaceAdapter(data)
        adapter.setOnItemClickCallBack(object : PlaceAdapter.OnItemClickCallback {
            override fun onItemClicked(place: Place) {
                val intent = Intent(requireContext(), DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_ID, place.id)
                startActivity(intent)
            }
        })
        binding.recyclerViewPlace.adapter = adapter
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun navigateToMaps() {
        val intent = Intent(activity, DetailActivity::class.java)
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}