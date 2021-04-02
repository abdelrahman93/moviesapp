package com.task.movieapp.ui.movies

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.orange.offers.ui.home4g.offerlist.adapter.MoviesAdapter
import com.orange.offers.ui.home4g.offerlist.adapter.SearchMoviesAdapter
import com.task.movieapp.BaseFragment
import com.task.movieapp.BaseViewState
import com.task.movieapp.R
import com.task.movieapp.di.component.DaggerAppComponent
import kotlinx.android.synthetic.main.fragment_movies.*
import kotlinx.android.synthetic.main.search_bar_layout.*
import kotlinx.android.synthetic.main.search_list_layout.*
import java.io.IOException


class MoviesFragment : BaseFragment<MoviesViewModel>(MoviesViewModel::class.java) {

    private lateinit var adapter: MoviesAdapter

    override fun injectDagger() = DaggerAppComponent.factory()
        .create(requireContext())
        .inject(this)


    override fun getLayout() = R.layout.fragment_movies

    override fun initView() {
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //pass
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                viewModel.search(movieName = s)
            }
        }
        etSearchbar.addTextChangedListener(textWatcher)

    }

    override fun renderView(viewState: BaseViewState?) {
        when (viewState) {
            is MoviesViewState.successMoviesList -> {
                adapter = MoviesAdapter(ArrayList())
                rvMoviesList.adapter = adapter
                adapter.addAll(viewState.moviesList.movies.subList(0, 10))
            }

            is MoviesViewState.HideSearchList -> {
                search_section.visibility = View.GONE
                rvMoviesList.visibility = View.VISIBLE
            }
            is MoviesViewState.ShowSearchList -> {
                search_section.visibility = View.VISIBLE
                rvMoviesList.visibility = View.GONE
            }
            is MoviesViewState.SearchResult -> {
                var adapter = SearchMoviesAdapter(ArrayList())
                rvSearchMoviesList.adapter = adapter
                adapter.addAll(viewState.searchResult)
            }

        }
    }

    override fun startRequest() {
        getJsonDataFromAsset(requireContext())?.let { viewModel.getMoviesList(it) }
    }

    override fun actions() {

    }

    fun getJsonDataFromAsset(context: Context): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open("movies.json").bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }


}





