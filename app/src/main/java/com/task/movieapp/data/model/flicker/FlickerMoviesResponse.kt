package com.task.movieapp.data.model.flicker

import com.google.gson.annotations.SerializedName

data class FlickerMoviesResponse(

	@field:SerializedName("stat")
	val stat: String? = null,

	@field:SerializedName("photos")
	val photos: Photos? = null
)