package com.example.clastic.data.entity

import com.google.gson.annotations.SerializedName

data class Response(

	@field:SerializedName("probability")
	val probability: String,

	@field:SerializedName("prediction")
	val prediction: String
)

