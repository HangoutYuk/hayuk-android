package com.mfarhan08a.hangoutyuk.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite")
class FavoriteEntity (
    @field:ColumnInfo(name = "id")
    @field:PrimaryKey
    var id: String,

    @field:ColumnInfo(name = "photo")
    val photo: String,

    @field:ColumnInfo(name = "name")
    val name: String,

    @field:ColumnInfo(name = "category")
    val category: String,

    @field:ColumnInfo(name = "rating")
    val rating: Double,

    @field:ColumnInfo(name = "totalReview")
    val totalReview: Int,

    @field:ColumnInfo(name = "latitude")
    val latitude: Double,

    @field:ColumnInfo(name = "longitude")
    val longitude: Double,
)