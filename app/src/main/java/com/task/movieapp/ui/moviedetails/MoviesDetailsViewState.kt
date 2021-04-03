package com.task.movieapp.ui.moviedetails

import com.task.movieapp.BaseViewState
import com.task.movieapp.data.model.MoviesByYear
import com.task.movieapp.data.model.MoviesList
import com.task.movieapp.data.model.flicker.PhotoItem

/**
 * Created by Abdelrahman Sherif on 4/2/2021.
 */
class MoviesDetailsViewState : BaseViewState() {

    class successGetMoviePhotos(val moviesPhotosList:List<PhotoItem?>?):BaseViewState()


}