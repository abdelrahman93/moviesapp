package com.task.movieapp.ui.movies

import android.text.Layout
import android.widget.EditText
import com.task.movieapp.BaseViewModel
import com.task.movieapp.data.Loader
import com.task.movieapp.di.module.SCHEDULER_IO
import com.task.movieapp.di.module.SCHEDULER_MAIN_THREAD
import com.task.movieapp.domain.usecase.movies.MovieUseCase
import io.reactivex.Scheduler
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by Abdelrahman Sherif on 3/30/2021.
 */
class MoviesViewModel @Inject constructor(
    private val ingredientsUseCase: MovieUseCase,
    @Named(SCHEDULER_IO) private val schedulerIo: Scheduler,
    @Named(SCHEDULER_MAIN_THREAD) private val schedulerMain: Scheduler
) : BaseViewModel(schedulerIo, schedulerMain) {


}