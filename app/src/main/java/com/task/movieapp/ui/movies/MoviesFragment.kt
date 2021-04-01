package com.task.movieapp.ui.movies

import com.task.movieapp.BaseFragment
import com.task.movieapp.BaseViewState
import com.task.movieapp.R
import com.task.movieapp.di.component.DaggerAppComponent


class MoviesFragment : BaseFragment<MoviesViewModel>(MoviesViewModel::class.java) {


    override fun injectDagger() = DaggerAppComponent.factory()
        .create(requireContext())
        .inject(this)


    override fun getLayout() = R.layout.fragment_movies

    override fun initView() {

    }

    override fun renderView(viewState: BaseViewState?) {
        when (viewState) {

        }
    }


    override fun actions() {

    }


}





