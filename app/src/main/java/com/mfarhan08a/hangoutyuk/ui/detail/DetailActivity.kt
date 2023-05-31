package com.mfarhan08a.hangoutyuk.ui.detail

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.mfarhan08a.hangoutyuk.data.model.Place
import com.mfarhan08a.hangoutyuk.databinding.ActivityDetailBinding
import com.mfarhan08a.hangoutyuk.ui.adapter.ReviewAdapter
import com.mfarhan08a.hangoutyuk.util.Formater
import com.mfarhan08a.hangoutyuk.util.ViewModelFactory

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    private val detailViewModel by viewModels<DetailViewModel> {
        ViewModelFactory.getInstance(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvReview.layoutManager = layoutManager

        val place = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_PLACE, Place::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_PLACE)
        }
        showDetail(place!!)


//        detailViewModel.getDetailPlace(id).observe(this) {
//            when (it) {
//                is Result.Loading -> {
//                    showLoading(true)
//                }
//                is Result.Success -> {
//                    showLoading(false)
//                    showDetail(it.data)
//                }
//                is Result.Error -> {
//                    showLoading(false)
//                }
//            }
//        }

        binding.apply {
            btnBack.setOnClickListener {
                finish()
            }
        }
    }

    private fun showDetail(data: Place) {
        data.apply {
            showAbout(about)
            showSchedule(emptyList())
            showContact(phone, website)
            binding.apply {
                val adapterReview = ReviewAdapter(review.take(3))
                rvReview.adapter = adapterReview
                Glide.with(this@DetailActivity)
                    .load(photo)
                    .into(ivDetailPhoto)
                tvDetailName.text = name
                tvDetailCategories.text = category
                tvDetailAddress.text = address
                tvContactPhone.text = phone
                tvContactWeb.text = website
                tvDetailRatingTotalReview.text =
                    "$rating (${Formater.totalReviewFormat(totalReview)})"
            }
        }

    }

    private fun showContact(phone: String, website: String) {
        binding.apply {
            if (phone != "null" || website != "null") {
                if (phone != "null") {
                    tvContactPhone.visibility = View.VISIBLE
                    tvContactPhone.text = phone

                } else {
                    cvPhone.visibility = View.GONE
                }
                if (website != "null") {
                    tvContactWeb.visibility = View.VISIBLE
                    tvContactWeb.text = phone
                } else {
                    cvWeb.visibility = View.GONE
                }
            } else {
                contactLayout.visibility = View.GONE
                div4.visibility = View.GONE
            }
        }
    }

    private fun showAbout(about: String) {
        binding.apply {
            if (about != "null") {
                tvDetailAbout.visibility = View.VISIBLE
                tvDetailAbout.text = about
            } else {
                tvAbout.visibility = View.GONE
                tvDetailAbout.visibility = View.GONE
                div2.visibility = View.GONE
            }
        }
    }

    private fun showSchedule(schedule: List<String>) {
        binding.apply {
            if (!schedule.isNullOrEmpty()) {

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

    companion object {
        const val EXTRA_PLACE = "extra_place"
    }
}