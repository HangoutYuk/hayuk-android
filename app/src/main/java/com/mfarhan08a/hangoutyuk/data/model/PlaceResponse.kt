package com.mfarhan08a.hangoutyuk.data.model

import com.google.gson.annotations.SerializedName

data class PlaceResponse(

	@field:SerializedName("data")
	val data: List<PlaceItem>,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: String
)

data class PlaceItem(

	@field:SerializedName("latitude")
	val latitude: Double,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("rating")
	val rating: Double,

	@field:SerializedName("photo")
	val photo: String? = null,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("category")
	val category: String,

	@field:SerializedName("totalReview")
	val totalReview: Int,

	@field:SerializedName("longitude")
	val longitude: Double
)
