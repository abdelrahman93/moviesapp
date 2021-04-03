package com.task.movieapp.domain.usecase.movies

import com.task.movieapp.data.model.MoviesList
import com.task.movieapp.data.model.flicker.FlickerMoviesResponse
import io.reactivex.Observable


/**
 * Created by Abdelrahman Sherif on 3/30/2021.
 */
interface MovieUseCase {
    fun getMoviesList(jsonFileString:String): Observable<MoviesList>
    fun getMoviePhotos(movieTitle: String): Observable<FlickerMoviesResponse>

}