package com.task.movieapp.data.repository

import com.task.movieapp.data.model.flicker.FlickerMoviesResponse
import com.task.movieapp.domain.remote.FlickerAPI
import com.task.movieapp.domain.repository.Repository
import com.task.movieapp.utilities.Constants.Companion.FLICKER_FORMAT
import com.task.movieapp.utilities.Constants.Companion.FLICKER_KEY
import com.task.movieapp.utilities.Constants.Companion.FLICKER_METHOD
import io.reactivex.Observable
import javax.inject.Inject

class RepositoryImp @Inject constructor(var flickerAPI: FlickerAPI) : Repository {

    override fun getMoviePhotos(movieTitle: String)
            : Observable<FlickerMoviesResponse> {
        return flickerAPI.getMoviePhotos(
            FLICKER_METHOD,
            FLICKER_KEY,
            FLICKER_FORMAT,
            1,
            1,10,
            movieTitle
        )
    }

}