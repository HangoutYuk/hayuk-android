package com.mfarhan08a.hangoutyuk.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mfarhan08a.hangoutyuk.data.model.Review
import com.mfarhan08a.hangoutyuk.databinding.ItemReviewBinding

class ReviewAdapter(private val listReview: List<Review>) :
    RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>() {


    class ReviewViewHolder(private val binding: ItemReviewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(review: Review) {
            binding.itemUsername.text = review.author
            binding.itemReview.text = review.text
            binding.itemRating.text = review.rating.toString()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ReviewViewHolder {
        val binding = ItemReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReviewViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ReviewViewHolder,
        position: Int
    ) {
        holder.bind(listReview[position])
    }

    override fun getItemCount(): Int = listReview.size

}