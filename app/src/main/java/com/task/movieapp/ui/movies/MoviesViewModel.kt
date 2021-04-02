package com.task.movieapp.ui.movies

import com.task.movieapp.BaseViewModel
import com.task.movieapp.data.Loader
import com.task.movieapp.data.model.MoviesByYear
import com.task.movieapp.data.model.MoviesItem
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
    private val movieUseCase: MovieUseCase,
    @Named(SCHEDULER_IO) private val schedulerIo: Scheduler,
    @Named(SCHEDULER_MAIN_THREAD) private val schedulerMain: Scheduler
) : BaseViewModel(schedulerIo, schedulerMain) {
    private lateinit var _moviesList: List<MoviesItem>


    fun getMoviesList(jsonFileString: String) {
        subscribe(request = movieUseCase.getMoviesList(jsonFileString),
            loader = Loader.Progress(true),
            success = io.reactivex.functions.Consumer { moviesList ->
                internalState.value = MoviesViewState.successMoviesList(moviesList)
                _moviesList = moviesList.movies
            }
        )
    }

    fun search(movieName: CharSequence?) = if (movieName?.isNotEmpty()!!) {
        internalState.value = MoviesViewState.ShowSearchList

        movieName.let {
            val searchResult = _moviesList.filter {
                it.title.contains(movieName, ignoreCase = true)
            }
            val groupby = searchResult.groupBy { it.year }
            val moviesListByYear = ArrayList<MoviesByYear>()

            for (item in groupby) {
                val list = item.value as ArrayList<MoviesItem>
                list.sortByDescending { it.rating }
                moviesListByYear.add(MoviesByYear(list.take(5), item.key))
            }



            internalState.value = MoviesViewState.SearchResult(moviesListByYear)
        }
    } else {
        internalState.value = MoviesViewState.HideSearchList
    }


}