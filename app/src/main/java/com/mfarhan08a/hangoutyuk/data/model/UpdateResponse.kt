package com.mfarhan08a.hangoutyuk.data.model

import com.google.gson.annotations.SerializedName

data class UpdateResponse(

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: String
)
