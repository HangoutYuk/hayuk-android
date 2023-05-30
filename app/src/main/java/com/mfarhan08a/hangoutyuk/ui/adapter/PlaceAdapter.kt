package com.mfarhan08a.hangoutyuk.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mfarhan08a.hangoutyuk.data.model.Place
import com.mfarhan08a.hangoutyuk.databinding.ItemPlaceBinding

class PlaceAdapter(private val listPlace: List<Place>) :
    RecyclerView.Adapter<PlaceAdapter.PlaceViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(
            place: Place,
//            optionsCompat: ActivityOptionsCompat
        )
    }

    fun setOnItemClickCallBack(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class PlaceViewHolder(private val binding: ItemPlaceBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(place: Place) {
            Glide.with(itemView.context)
                .load(place.photoUrl)
                .into(binding.itemImage)
            binding.itemName.text = place.name
            binding.itemCategory.text = place.category
            binding.itemRating.text = place.rating.toString()
            binding.itemTotalReview.text = place.totalReview.toString()
            binding.itemDistance.text = place.distance.toString()
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PlaceViewHolder {
        val binding = ItemPlaceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlaceViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: PlaceViewHolder,
        position: Int
    ) {
        holder.bind(listPlace[position])
        holder.itemView.setOnClickListener {
//            val optionsCompat: ActivityOptionsCompat =
//                ActivityOptionsCompat.makeSceneTransitionAnimation(
//                    holder.itemView.context as Activity,
//                    Pair(holder.ivPhoto, "photo"),
//                    Pair(holder.tvName, "name"),
//                    Pair(holder.tvDescription, "description")
//                )
            onItemClickCallback.onItemClicked(
                listPlace[holder.adapterPosition],
//                optionsCompat
            )
        }

    }

    override fun getItemCount(): Int = listPlace.size

}
