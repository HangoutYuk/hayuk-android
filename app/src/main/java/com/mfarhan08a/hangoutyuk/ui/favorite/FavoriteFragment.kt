package com.mfarhan08a.hangoutyuk.ui.favorite

import android.content.Intent
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mfarhan08a.hangoutyuk.R
import com.mfarhan08a.hangoutyuk.data.local.entity.FavoriteEntity
import com.mfarhan08a.hangoutyuk.data.model.PlaceItem
import com.mfarhan08a.hangoutyuk.databinding.FragmentFavoriteBinding
import com.mfarhan08a.hangoutyuk.ui.adapter.PlaceAdapter
import com.mfarhan08a.hangoutyuk.ui.detail.DetailActivity
import com.mfarhan08a.hangoutyuk.ui.home.HomeFragment
import com.mfarhan08a.hangoutyuk.ui.home.HomeFragment.Companion.dataUser
import com.mfarhan08a.hangoutyuk.ui.mapsfavorite.MapsFavoriteActivity
import com.mfarhan08a.hangoutyuk.util.ViewModelFactory

class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    private val favoriteViewModel by viewModels<FavoriteViewModel> {
        ViewModelFactory.getInstance(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.recyclerViewPlace.layoutManager = layoutManager
        binding.recyclerViewPlace.adapter = null

        favoriteViewModel.getFavoritePlaces().observe(requireActivity()) { data ->
            try {
                if (data.isNotEmpty()) {
                    showLoading(true)
                    showEmptyText(false)
                    setPlacesData(data, HomeFragment.location!!)
                    showLoading(false)
                    binding.btnMaps.setOnClickListener {
                        navigateToMaps()
                    }
                } else {
                    showEmptyText(true)
                    binding.btnMaps.setOnClickListener {
                        Toast.makeText(
                            requireContext(),
                            getString(R.string.empty_favorite),
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                }
            } catch (e: Exception) {
                Log.d(TAG, e.toString())
            }
        }
    }

    private fun showEmptyText(isEmpty: Boolean) {
        try {
            binding.textViewEmpty.visibility = if (isEmpty) View.VISIBLE else View.GONE
            binding.recyclerViewPlace.visibility = if (isEmpty) View.GONE else View.VISIBLE
        } catch (e: Exception) {
            Log.d(TAG, e.toString())
        }
    }

    private fun navigateToMaps() {
        val intent = Intent(activity, MapsFavoriteActivity::class.java)
        startActivity(intent)
    }

    private fun setPlacesData(data: List<FavoriteEntity>, location: Location) {
        val favoritePlaces = ArrayList<PlaceItem>()
        data.forEach {
            favoritePlaces.add(
                PlaceItem(
                    id = it.id,
                    photo = it.photo,
                    name = it.name,
                    category = it.category,
                    rating = it.rating,
                    totalReview = it.totalReview,
                    latitude = it.latitude,
                    longitude = it.longitude,
                )
            )
        }

        val adapter = PlaceAdapter(favoritePlaces, location)
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

    private fun showLoading(isLoading: Boolean) {
        try {
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        } catch (e: Exception) {
            Log.d(TAG, e.toString())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private val TAG = FavoriteFragment::class.java.simpleName
    }
}