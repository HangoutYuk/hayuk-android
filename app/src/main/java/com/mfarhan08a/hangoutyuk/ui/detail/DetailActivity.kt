package com.mfarhan08a.hangoutyuk.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.mfarhan08a.hangoutyuk.R
import com.mfarhan08a.hangoutyuk.data.Result
import com.mfarhan08a.hangoutyuk.data.model.Place
import com.mfarhan08a.hangoutyuk.databinding.ActivityDetailBinding
import com.mfarhan08a.hangoutyuk.ui.adapter.ReviewAdapter
import com.mfarhan08a.hangoutyuk.ui.adapter.ScheduleAdapter
import com.mfarhan08a.hangoutyuk.util.Formater
import com.mfarhan08a.hangoutyuk.util.ViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private var isPlaceFavorite: Boolean = false

    private val detailViewModel by viewModels<DetailViewModel> {
        ViewModelFactory.getInstance(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvReview.layoutManager = layoutManager

        val placeId = intent.getStringExtra(EXTRA_PLACE_ID)
        val userId = intent.getStringExtra(EXTRA_USER_ID)
        Log.d(TAG, "placeid: $placeId")
        Log.d(TAG, "userId: $userId")

        detailViewModel.apply {

            lifecycleScope.launch(Dispatchers.IO) {
                isPlaceFavorite = isFavorited(placeId!!)
                displayFavoriteButton(isPlaceFavorite)
            }

            getToken().observe(this@DetailActivity) { token ->
                if (!token.isNullOrEmpty() && placeId?.isNotEmpty()!!) {
                    getPlaceDetail(token, placeId).observe(this@DetailActivity) {
                        when (it) {
                            is Result.Loading -> {
                                showLoading(true)
                                Log.d(TAG, "loading..")

                            }
                            is Result.Success -> {
                                showDetail(it.data.data)
                                showLoading(false)
                                Log.d(TAG, "success..")
                                binding.btnFav.setOnClickListener { _ ->
                                    favorite(it.data.data)
                                }
                                binding.fabPoll.setOnClickListener {
                                    pollPlace(token, placeId, userId)
                                }
                            }
                            is Result.Error -> {
                                showLoading(false)
                                Log.d(TAG, "error: ${it.error}")
                                Toast.makeText(
                                    this@DetailActivity,
                                    "getplace error: ${it.error}",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                            else -> {
                                Toast.makeText(
                                    this@DetailActivity,
                                    getString(R.string.error_detail),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                } else {
                    Toast.makeText(
                        this@DetailActivity, "error id or token", Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        binding.apply {
            btnBack.setOnClickListener {
                finish()
            }
        }
    }

    private fun pollPlace(token: String, placeId: String, userId: String?) {
        Log.d(TAG, "token: $token, placeid: $placeId, userid: $userId")
        detailViewModel.createPoll(token, placeId, userId!!).observe(this) {
            when (it) {
                is Result.Loading -> {
                    showLoading(true)
                    Log.d(TAG, "loading..")

                }
                is Result.Success -> {
                    showLoading(false)
                    val intent = Intent(Intent.ACTION_SEND)
                    intent.type = "text/plain"
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Poll For A Place With HangoutYuk!")
                    intent.putExtra(Intent.EXTRA_TEXT, "Poll For A Place With HangoutYuk!\n${it.data.data}")
                    val chooser = Intent.createChooser(intent, "Share with..")
                    startActivity(chooser)
                    Log.d(TAG, "success..")
                }
                is Result.Error -> {
                    showLoading(false)
                    Log.d(TAG, "error: ${it.error}")
                    Toast.makeText(
                        this@DetailActivity,
                        "getplace error: ${it.error}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else -> {
                    Toast.makeText(
                        this@DetailActivity,
                        getString(R.string.error_detail),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun displayFavoriteButton(isFav: Boolean) {
        binding.btnFav.setImageResource(
            if (isFav) R.drawable.ic_favorited else R.drawable.ic_favorite
        )
    }

    private fun favorite(data: List<Place?>?) {
        val dataPlace = data?.get(0)!!
        dataPlace.apply {
            if (isPlaceFavorite) {
                Snackbar.make(binding.root, "Place not favorite", Snackbar.LENGTH_SHORT).show()
                detailViewModel.deleteFavorite(id!!)
            } else {
                Snackbar.make(binding.root, "Place Favorited", Snackbar.LENGTH_SHORT).show()
                detailViewModel.addToFavorite(this)
            }
            isPlaceFavorite = !isPlaceFavorite
            displayFavoriteButton(isPlaceFavorite)
        }
    }

    private fun showDetail(data: List<Place?>?) {
        val dataPlace = data?.get(0)!!
        dataPlace.apply {
            showAbout(about)
            showSchedule(schedule)
            showContact(phone, website)
            binding.apply {
                if (name != null) {
                    tvDetailName.text = name
                }
                if (category != null) {
                    tvDetailCategories.text = Formater.formatCategories(category)
                }
                if (address != null) {
                    tvDetailAddress.text = address
                }
                if (!review.isNullOrEmpty()) {
                    val adapterReview = ReviewAdapter(review)
                    rvReview.adapter = adapterReview
                }
                if (photo != null) {
                    Glide.with(this@DetailActivity).load(photo).into(ivDetailPhoto)
                } else {
                    ivDetailPhoto.setImageResource(R.drawable.no_image)
                }
                if (totalReview != null) {
                    tvDetailRatingTotalReview.text =
                        "$rating ${Formater.totalReviewFormat(totalReview)}"
                }
                if (mapsURL != null) {
                    fabMaps.setOnClickListener {
                        val mapUrl = Uri.parse(mapsURL)
                        val mapIntent = Intent(Intent.ACTION_VIEW, mapUrl)
                        startActivity(mapIntent)
                    }
                }
            }
        }
    }

    private fun showContact(phone: String?, website: String?) {
        binding.apply {
            if (phone != null || website != null) {
                if (phone != null) {
                    tvContactPhone.visibility = View.VISIBLE
                    tvContactPhone.text = phone

                } else {
                    cvPhone.visibility = View.GONE
                }
                if (website != null) {
                    tvContactWeb.visibility = View.VISIBLE
                    tvContactWeb.text = website
                } else {
                    cvWeb.visibility = View.GONE
                }
            } else {
                contactLayout.visibility = View.GONE
                div4.visibility = View.GONE
            }
        }
    }

    private fun showAbout(about: String?) {
        binding.apply {
            if (about != null) {
                tvDetailAbout.visibility = View.VISIBLE
                tvDetailAbout.text = about
            } else {
                tvAbout.visibility = View.GONE
                tvDetailAbout.visibility = View.GONE
                div2.visibility = View.GONE
            }
        }
    }

    private fun showSchedule(schedule: List<List<String>?>) {
        if (schedule[0] != null) {
            val sch = schedule[0]
            binding.apply {
                if (sch?.get(0) != null) {
                    val scheduleAdapter = ScheduleAdapter(sch)
                    binding.rvSchedule.adapter = scheduleAdapter
                }
            }
        } else {
            binding.tvSchedule.visibility = View.GONE
            binding.rvSchedule.visibility = View.GONE
            binding.div3.visibility = View.GONE
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    companion object {
        private val TAG = DetailActivity::class.java.simpleName
        const val EXTRA_PLACE_ID = "extra_place_id"
        const val EXTRA_USER_ID = "extra_user_id"
    }
}