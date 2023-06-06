package com.mfarhan08a.hangoutyuk.data.model

import com.google.gson.annotations.SerializedName

data class PhotoResponse(

	@field:SerializedName("data")
	val data: Data,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: String
)

data class Data(

	@field:SerializedName("photoURL")
	val photoURL: String
)
