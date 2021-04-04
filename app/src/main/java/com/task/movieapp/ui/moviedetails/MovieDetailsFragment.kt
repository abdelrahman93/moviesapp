package com.task.movieapp.ui.moviedetails

import androidx.navigation.fragment.navArgs
import com.task.movieapp.BaseFragment
import com.task.movieapp.BaseViewState
import com.task.movieapp.R
import com.task.movieapp.data.model.flicker.PhotoItem
import com.task.movieapp.di.component.DaggerAppComponent
import com.task.movieapp.ui.moviedetails.adapter.MoviesPhotosAdapter
import kotlinx.android.synthetic.main.fragment_detail_movie.*


class MovieDetailsFragment :
    BaseFragment<MoviesDetailsViewModel>(MoviesDetailsViewModel::class.java) {

    private val args: MovieDetailsFragmentArgs by navArgs()

    override fun injectDagger() = DaggerAppComponent.factory()
        .create(requireContext())
        .inject(this)


    override fun getLayout() = R.layout.fragment_detail_movie

    override fun initView() {
        bindMovieDetails()
    }

    override fun renderView(viewState: BaseViewState?) {
        when (viewState) {

            is MoviesDetailsViewState.successGetMoviePhotos -> {

                if (viewState.moviesPhotosList != null && viewState.moviesPhotosList.isNotEmpty()) {
                    rvPostersMovie.adapter =
                        MoviesPhotosAdapter(viewState.moviesPhotosList as ArrayList<PhotoItem>)
                }
            }
        }
    }

    override fun startRequest() {
        viewModel.getMoviePhotos(args.movie.title)
    }

    override fun actions() {

    }

    private fun bindMovieDetails() {
        tvDetailsMovieName.text = args.movie.title
        tvDetailsMovieYear.text = args.movie.year.toString()
        tvDetailsMovieRating.rating = args.movie.rating?.toFloat()!!
        tvDetailsMovieGenres.text = args.movie.genres?.joinToString(separator = " , ")
        tvDetailsMovieCast.text = String.format(
            getString(R.string.cast),
            args.movie.cast?.joinToString(separator = " , ")
        )

    }


}





