package com.mfarhan08a.hangoutyuk.data.model

import com.google.gson.annotations.SerializedName

data class DeletePollRequest(
    @SerializedName("pollId") val pollId: String
)