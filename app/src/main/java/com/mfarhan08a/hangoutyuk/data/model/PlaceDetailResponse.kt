package com.mfarhan08a.hangoutyuk.data.model

import com.google.gson.annotations.SerializedName

data class PlaceDetailResponse(

	@field:SerializedName("data")
	val data: List<Place?>? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class Review(

	@field:SerializedName("author")
	val author: String? = null,

	@field:SerializedName("rating")
	val rating: Int? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("text")
	val text: String? = null,

	@field:SerializedName("time")
	val time: String? = null
)

data class Place(

	@field:SerializedName("website")
	val website: String? = null,

	@field:SerializedName("address")
	val address: String? = null,

	@field:SerializedName("latitude")
	val latitude: Double? = null,

	@field:SerializedName("rating")
	val rating: Double? = null,

	@field:SerializedName("about")
	val about: String? = null,

	@field:SerializedName("photo")
	val photo: String? = null,

	@field:SerializedName("schedule")
	val schedule: List<List<String>?>,

	@field:SerializedName("phone")
	val phone: String? = null,

	@field:SerializedName("mapsURL")
	val mapsURL: String? = null,

	@field:SerializedName("review")
	val review: List<Review?>? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("category")
	val category: String? = null,

	@field:SerializedName("totalReview")
	val totalReview: Int? = null,

	@field:SerializedName("longitude")
	val longitude: Double? = null
)
