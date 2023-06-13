package com.mfarhan08a.hangoutyuk.data.model

import com.google.gson.annotations.SerializedName

data class PollResponse(

	@field:SerializedName("data")
	val data: String,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: String
)
