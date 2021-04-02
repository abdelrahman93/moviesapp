package com.task.movieapp.data.model

import com.google.gson.annotations.SerializedName

data class MoviesList(

	@field:SerializedName("movies")
	val movies: List<MoviesItem>
)