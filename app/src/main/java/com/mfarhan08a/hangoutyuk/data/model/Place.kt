package com.mfarhan08a.hangoutyuk.data.model


data class Place(
    val id: Int,
    val distance: Double,
    val photoUrl: String,
    val name: String,
    val category: String,
    val address: String,
    val rating: Double,
    val totalReview: Double,
    val about: String,
    val review: List<Review>,
    val contactPhone: String,
    val contactWeb: String,
//    val url: String,
//    val lat: Double,
//    val lon: Double,
)

data class Review(
    val id: Int,
    val rating: Double,
    val username: String,
    val reviewDetail: String,
)