package com.mfarhan08a.hangoutyuk.ui.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mfarhan08a.hangoutyuk.R
import com.mfarhan08a.hangoutyuk.data.model.PollItem
import com.mfarhan08a.hangoutyuk.databinding.ItemPollBinding
import com.mfarhan08a.hangoutyuk.util.Formater

class PollAdapter(
    private val listPoll: List<PollItem>,
    private val context: Context,
    private val onDeleteClickListener: OnDeleteClickListener,
) : RecyclerView.Adapter<PollAdapter.ViewHolder>() {

    interface OnDeleteClickListener {
        fun onDeleteClick(pollId: String)
    }

    class ViewHolder(private val binding: ItemPollBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            poll: PollItem,
            context: Context,
            onDeleteClickListener: OnDeleteClickListener
        ) {
            if (poll.photoUrl != null) {
                Glide.with(itemView.context)
                    .load(poll.photoUrl)
                    .into(binding.itemHistoryPhoto)
            } else {
                binding.itemHistoryPhoto.setImageResource(R.drawable.no_image)
            }
            binding.itemName.text = poll.placeName
            binding.itemRating.text = poll.placeRating
            binding.itemTotalReview.text = Formater.totalReviewFormat(poll.placeTotalReview.toInt())

            binding.btnShare.setOnClickListener {
                val intent = Intent(Intent.ACTION_SEND)
                intent.type = "text/plain"
                intent.putExtra(Intent.EXTRA_SUBJECT, "Poll For A Place With HangoutYuk!")
                intent.putExtra(
                    Intent.EXTRA_TEXT,
                    "Poll For A Place With HangoutYuk!\nhttps://poll-dot-hayuk-project.et.r.appspot.com/poll/${poll.pollId}"
                )
                val chooser = Intent.createChooser(intent, "Share with..")
                context.startActivity(chooser)

            }
            binding.btnVisit.setOnClickListener {
                val visitUrl =
                    Uri.parse("https://poll-dot-hayuk-project.et.r.appspot.com/poll/${poll.pollId}")
                val mapIntent = Intent(Intent.ACTION_VIEW, visitUrl)
                context.startActivity(mapIntent)
            }
            binding.btnDelete.setOnClickListener {
                onDeleteClickListener.onDeleteClick(poll.pollId)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ViewHolder {
        val binding = ItemPollBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.bind(listPoll[position], context, onDeleteClickListener)
    }

    override fun getItemCount(): Int = listPoll.size

}
