package com.task.movieapp.ui.moviedetails

import android.util.Log
import com.task.movieapp.BaseViewModel
import com.task.movieapp.data.Loader
import com.task.movieapp.data.model.flicker.PhotoItem
import com.task.movieapp.di.module.SCHEDULER_IO
import com.task.movieapp.di.module.SCHEDULER_MAIN_THREAD
import com.task.movieapp.domain.usecase.movies.MovieUseCase
import io.reactivex.Scheduler
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by Abdelrahman Sherif on 2/4/2021.
 */
class MoviesDetailsViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase,
    @Named(SCHEDULER_IO) private val schedulerIo: Scheduler,
    @Named(SCHEDULER_MAIN_THREAD) private val schedulerMain: Scheduler
) : BaseViewModel(schedulerIo, schedulerMain) {


    fun getMoviePhotos(title: String) {
        subscribe(request = movieUseCase.getMoviePhotos(title),
            loader = Loader.Progress(true),
            success = io.reactivex.functions.Consumer { moviesPhotos ->
                internalState.value =
                    MoviesDetailsViewState.successGetMoviePhotos(moviesPhotos.photos?.photo)
            }
        )
    }




}