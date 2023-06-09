package com.mfarhan08a.hangoutyuk.ui.adapter

import android.location.Location
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mfarhan08a.hangoutyuk.R
import com.mfarhan08a.hangoutyuk.data.model.PlaceItem
import com.mfarhan08a.hangoutyuk.databinding.ItemPlaceBinding
import com.mfarhan08a.hangoutyuk.util.Formater

class PlaceAdapter(private val listPlace: List<PlaceItem>, private val userLocation: Location) :
    RecyclerView.Adapter<PlaceAdapter.PlaceViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(place: PlaceItem)
    }

    fun setOnItemClickCallBack(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class PlaceViewHolder(private val binding: ItemPlaceBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(place: PlaceItem, userLocation: Location) {
            if (place.photo != null) {
                Glide.with(itemView.context)
                    .load(place.photo)
                    .into(binding.itemImage)
            } else {
                binding.itemImage.setImageResource(R.drawable.no_image)
            }
            binding.itemName.text = place.name
            binding.itemCategory.text = Formater.formatCategories(place.category)
            binding.itemRating.text = place.rating.toString()
            binding.itemTotalReview.text = Formater.totalReviewFormat(place.totalReview)
            val placeLocation = Location("provider")
            placeLocation.latitude = place.latitude
            placeLocation.longitude = place.longitude
            binding.itemDistance.text = Formater.distanceFormat(placeLocation, userLocation)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): PlaceViewHolder {
        val binding = ItemPlaceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlaceViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: PlaceViewHolder,
        position: Int
    ) {
        holder.bind(listPlace[position], userLocation)
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(
                listPlace[holder.adapterPosition]
            )
        }

    }

    override fun getItemCount(): Int = listPlace.size

}
