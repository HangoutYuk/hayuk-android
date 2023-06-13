package com.mfarhan08a.hangoutyuk.data.model

import com.google.gson.annotations.SerializedName

data class PollResponse(

	@field:SerializedName("data")
	val data: List<PollItem>,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: String
)

data class PollItem(

	@field:SerializedName("place_name")
	val placeName: String,

	@field:SerializedName("poll_id")
	val pollId: String,

	@field:SerializedName("photo_url")
	val photoUrl: String,

	@field:SerializedName("place_total_review")
	val placeTotalReview: String,

	@field:SerializedName("place_rating")
	val placeRating: String
)
