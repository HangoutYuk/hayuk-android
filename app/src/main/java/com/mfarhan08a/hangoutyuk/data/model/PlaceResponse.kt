package com.mfarhan08a.hangoutyuk.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class PlaceResponse(

    @field:SerializedName("data")
    val data: List<Place>,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("status")
    val status: String
)

@Parcelize
data class Review(

    @field:SerializedName("author")
    val author: String,

    @field:SerializedName("rating")
    val rating: Double,

    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("text")
    val text: String,

    @field:SerializedName("time")
    val time: String
) : Parcelable

@Parcelize
data class Place(

    @field:SerializedName("website")
    val website: String,

    @field:SerializedName("address")
    val address: String,

    @field:SerializedName("latitude")
    val latitude: Double,

    @field:SerializedName("rating")
    val rating: Double,

    @field:SerializedName("about")
    val about: String,

    @field:SerializedName("photo")
    val photo: String,

    @field:SerializedName("phone")
    val phone: String,

    @field:SerializedName("review")
    val review: List<Review>,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("category")
    val category: String,

    @field:SerializedName("totalReview")
    val totalReview: Int,

    @field:SerializedName("longitude")
    val longitude: Double,
) : Parcelable
