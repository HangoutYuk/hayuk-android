package com.mfarhan08a.hangoutyuk.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.mfarhan08a.hangoutyuk.data.Result
import com.mfarhan08a.hangoutyuk.data.model.Place
import com.mfarhan08a.hangoutyuk.databinding.ActivityDetailBinding
import com.mfarhan08a.hangoutyuk.ui.adapter.ReviewAdapter
import com.mfarhan08a.hangoutyuk.ui.adapter.ScheduleAdapter
import com.mfarhan08a.hangoutyuk.util.Formater
import com.mfarhan08a.hangoutyuk.util.ViewModelFactory

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    private val detailViewModel by viewModels<DetailViewModel> {
        ViewModelFactory.getInstance(application)
    }

    companion object {
        private val TAG = DetailActivity::class.java.simpleName
        const val EXTRA_PLACE_ID = "extra_place_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvReview.layoutManager = layoutManager

        val placeId = intent.getStringExtra(EXTRA_PLACE_ID)
        Log.d(TAG, "placeid: $placeId")

        detailViewModel.apply {
            getToken().observe(this@DetailActivity) { token ->
                if (!token.isNullOrEmpty() && !placeId.isNullOrEmpty()) {
                    getPlaceDetail(token, placeId).observe(this@DetailActivity) {
                        when (it) {
                            is Result.Loading -> {
                                Log.d(TAG, "loading..")
                                showLoading(true)

                            }
                            is Result.Success -> {
                                showDetail(it.data.data)
                                showLoading(false)
                                Log.d(TAG, "success..")
                            }
                            is Result.Error -> {
                                showLoading(false)
                                Log.d(TAG, "error..")
                                Toast.makeText(
                                    this@DetailActivity,
                                    "getplace e: ${it.error}",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                            else -> {
                                Toast.makeText(
                                    this@DetailActivity,
                                    "Can't get detail",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                } else {
                    Toast.makeText(
                        this@DetailActivity,
                        "error id or token",
                        Toast.LENGTH_SHORT
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

    private fun showDetail(data: List<Place?>?) {
        val dataPlace = data?.get(0)!!
        Log.d(TAG, "photo: ${dataPlace.photo}")
        dataPlace.apply {
            showAbout(about)
            showSchedule(schedule)
            showContact(phone, website)
            binding.apply {
                tvDetailName.text = name
                tvDetailCategories.text = category
                tvDetailAddress.text = address
                if (!review.isNullOrEmpty()) {
                    val adapterReview = ReviewAdapter(review)
                    rvReview.adapter = adapterReview
                }
                if (photo != null) {
                    Glide.with(this@DetailActivity)
                        .load(photo)
                        .into(ivDetailPhoto)
                }
                if (totalReview != null) {
                    tvDetailRatingTotalReview.text =
                        "$rating (${Formater.totalReviewFormat(totalReview)})"
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

    private fun showSchedule(schedule: List<List<String?>?>?) {
        val sch = schedule?.get(0)!!
        binding.apply {
            if (!sch[0].isNullOrEmpty()) {
                val scheduleAdapter = ScheduleAdapter(sch)
                binding.rvSchedule.adapter = scheduleAdapter
            } else {
                tvSchedule.visibility = View.GONE
                rvSchedule.visibility = View.GONE
                div3.visibility = View.GONE
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}