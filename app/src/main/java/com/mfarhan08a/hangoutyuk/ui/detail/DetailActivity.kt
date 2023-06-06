package com.mfarhan08a.hangoutyuk.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvReview.layoutManager = layoutManager

        showLoading(true)
        val place = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_PLACE, Place::class.java)!!
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_PLACE)!!
        }
        if (place != null) {
            showDetail(place)
        } else {
            finish()
        }
        showLoading(false)

        binding.apply {
            btnBack.setOnClickListener {
                finish()
            }
        }
    }

    private fun showDetail(data: Place) {
        data.apply {
            showAbout(about!!)
            showSchedule(data.schedule!!)
            showContact(phone!!, website!!)
            binding.apply {
                val adapterReview = ReviewAdapter(review)
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
                fabMaps.setOnClickListener {
                    val mapUrl = Uri.parse(mapsURL)
                    val mapIntent = Intent(Intent.ACTION_VIEW, mapUrl)
                    startActivity(mapIntent)
                }
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

    private fun showSchedule(schedule: List<List<String>>) {
        val sch = schedule[0]
        binding.apply {
            if (sch[0] != "null") {
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

    companion object {
        const val EXTRA_PLACE = "extra_place"
    }
}