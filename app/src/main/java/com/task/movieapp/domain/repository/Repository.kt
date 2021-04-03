package com.task.movieapp.domain.repository

import com.task.movieapp.data.model.flicker.FlickerMoviesResponse
import io.reactivex.Observable


interface Repository {
    fun getMoviePhotos(movieTitle: String): Observable<FlickerMoviesResponse>

}