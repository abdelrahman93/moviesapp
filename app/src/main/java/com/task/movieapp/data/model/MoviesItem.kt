package com.task.movieapp.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MoviesItem(

	@field:SerializedName("cast")
	val cast: List<String?>? = null,

	@field:SerializedName("year")
	val year: Int? = null,

	@field:SerializedName("genres")
	val genres: List<String?>? = null,

	@field:SerializedName("rating")
	val rating: Int? = null,

	@field:SerializedName("title")
	val title: String = ""
) : Parcelable