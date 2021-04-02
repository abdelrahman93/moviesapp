package com.task.movieapp.data.model

import com.google.gson.annotations.SerializedName

data class MoviesByYear(
    val moviesListByYear: List<MoviesItem>,
    @field:SerializedName("year")
    val year: Int? = null
)