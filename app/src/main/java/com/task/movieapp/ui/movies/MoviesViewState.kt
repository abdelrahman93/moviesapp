package com.task.movieapp.ui.movies

import com.task.movieapp.BaseViewState
import com.task.movieapp.data.model.MoviesByYear
import com.task.movieapp.data.model.MoviesList

/**
 * Created by Abdelrahman Sherif on 3/30/2021.
 */
class MoviesViewState : BaseViewState() {

    class successMoviesList(val moviesList:MoviesList):BaseViewState()

    class SearchResult(val searchResult: List<MoviesByYear>) : BaseViewState()
    object ShowSearchList : BaseViewState()
    object HideSearchList : BaseViewState()

}