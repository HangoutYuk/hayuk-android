package com.mfarhan08a.hangoutyuk.util

object Formater {
    fun totalReviewFormat(totalReview: Int): String {
        return when {
            totalReview > 1000000 -> "${(totalReview.toDouble() / 1000000)} jt"
            totalReview > 1000 -> "${(totalReview.toDouble() / 1000)} rb"
            else -> totalReview.toString()
        }
    }
}