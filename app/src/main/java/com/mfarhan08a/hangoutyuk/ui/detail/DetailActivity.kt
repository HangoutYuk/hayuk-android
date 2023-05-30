package com.mfarhan08a.hangoutyuk.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.mfarhan08a.hangoutyuk.data.Result
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.mfarhan08a.hangoutyuk.R
import com.mfarhan08a.hangoutyuk.data.model.Place
import com.mfarhan08a.hangoutyuk.databinding.ActivityDetailBinding
import com.mfarhan08a.hangoutyuk.ui.adapter.ReviewAdapter
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

        val id = intent.getIntExtra(EXTRA_ID, 0)

        detailViewModel.getDetailPlace(id).observe(this) {
            when (it) {
                is Result.Loading -> {
                    showLoading(true)
                }
                is Result.Success -> {
                    showLoading(false)
                    showDetail(it.data)
                }
                is Result.Error -> {
                    showLoading(false)
                }
            }
        }

        binding.apply {
            btnBack.setOnClickListener {
                finish()
            }
        }
    }

    private fun showDetail(data: Place) {
        data.apply {
            binding.apply {
                val adapterReview = ReviewAdapter(review.take(3))
                rvReview.adapter = adapterReview
                Glide.with(this@DetailActivity)
                    .load(photoUrl)
                    .into(ivDetailPhoto)
                tvDetailName.text = name
                tvDetailCategories.text = category
                tvDetailAddress.text = address
                tvDetailAbout.text = about
                tvContactPhone.text = contactPhone
                tvContactWeb.text = contactWeb
                tvDetailRatingTotalReview.text = "${rating} (${totalReview})"
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    companion object {
        const val EXTRA_ID = "extra_id"
    }
}