package com.mfarhan08a.hangoutyuk.util

import android.location.Location
import java.text.DecimalFormat

object Formater {
    fun totalReviewFormat(totalReview: Int): String {
        return when {
            totalReview > 1000000 -> "(${(totalReview.toDouble() / 1000000)} jt)"
            totalReview > 1000 -> "(${(totalReview.toDouble() / 1000)} rb)"
            else -> "($totalReview)"
        }
    }

    private fun calculateDistance(location1: Location, location2: Location): Float {
        return location1.distanceTo(location2)
    }

    fun distanceFormat(location1: Location, location2: Location): String {
        val distance = calculateDistance(location1, location2)
        val df = DecimalFormat("#.##")
        val df1 = DecimalFormat("##.#")
        val df2 = DecimalFormat("###.#")
        return when {
            distance > 100000 -> "${df2.format(distance / 1000)} km away"
            distance > 10000 -> "${df1.format(distance / 1000)} km away"
            else -> "${df.format(distance / 1000)} km away"
        }
    }

    fun formatCategories(input: String): String {
        val words = input.split("_")
        val formattedWords = words.map { it.capitalize() }
        return formattedWords.joinToString(" ")
    }
}