package com.task.movieapp.domain.usecase.movies

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.task.movieapp.data.model.MoviesList
import com.task.movieapp.data.model.flicker.FlickerMoviesResponse
import com.task.movieapp.domain.repository.Repository
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by Abdelrahman Sherif on 3/30/2021.
 */
class MovieUseCaseImp @Inject

constructor(val repository: Repository) : MovieUseCase {

    override fun getMoviesList(jsonFileString: String): Observable<MoviesList> {
        val gson = Gson()
        val listMovieType = object : TypeToken<MoviesList>() {}.type
        val moviesList: MoviesList = gson.fromJson(jsonFileString, listMovieType)
        return Observable.just(moviesList)
    }

    override fun getMoviePhotos(movieTitle: String)
            : Observable<FlickerMoviesResponse> {
        return repository.getMoviePhotos(movieTitle)
    }

}