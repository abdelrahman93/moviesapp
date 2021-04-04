package com.task.movieapp.ui.movies

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.task.movieapp.BaseFragment
import com.task.movieapp.BaseViewState
import com.task.movieapp.R
import com.task.movieapp.adapter.SearchMoviesAdapter
import com.task.movieapp.data.model.MoviesItem
import com.task.movieapp.di.component.DaggerAppComponent
import com.task.movieapp.ui.movies.adapter.MoviesAdapter
import com.task.movieapp.utilities.getJsonDataFromAsset
import kotlinx.android.synthetic.main.fragment_movies.*
import kotlinx.android.synthetic.main.search_bar_layout.*
import kotlinx.android.synthetic.main.search_list_layout.*


class MoviesFragment : BaseFragment<MoviesViewModel>(MoviesViewModel::class.java) {

    private var pastVisiblesItems = 0
    private var visibleItemCount: Int = 0
    private var totalItemCount: Int = 0
    private var start: Int = 0
    private var end: Int = 10

    private var layoutManager: LinearLayoutManager? = null

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

        layoutManager = LinearLayoutManager(activity)
        rvMoviesList.layoutManager = layoutManager


    }

    override fun renderView(viewState: BaseViewState?) {
        when (viewState) {
            is MoviesViewState.successMoviesList -> {
                initMoviesList(viewState.moviesList.movies)
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
                val adapter = SearchMoviesAdapter(ArrayList()) {
                    onMovieClick(it)
                }
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


    private fun onMovieClick(movie: MoviesItem) {
        navigateToMovieDetails(movie)
    }

    private fun navigateToMovieDetails(movie: MoviesItem) {
        etSearchbar.editableText.clear()
        val action = MoviesFragmentDirections.toMovieDetails(movie)
        findNavController().navigate(action)
    }

    //Movie List Pagination
    private fun paginationFunction(moviesList: List<MoviesItem>) {
        rvMoviesList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val finalLayoutManager: LinearLayoutManager? = layoutManager
                if (dy > 0) {
                    visibleItemCount = finalLayoutManager?.childCount!!
                    totalItemCount = finalLayoutManager.itemCount
                    pastVisiblesItems = (finalLayoutManager).findFirstVisibleItemPosition()
                    if (visibleItemCount + pastVisiblesItems >= totalItemCount) {
                        onLoadMore(moviesList)
                    }
                }

            }
        })
    }

    private fun initMoviesList(moviesList: List<MoviesItem>) {
        adapter = MoviesAdapter(ArrayList()) {
            onMovieClick(it)
        }
        rvMoviesList.adapter = adapter
        adapter.addAll(moviesList.subList(start, end))
        paginationFunction(moviesList)
    }

    private fun onLoadMore(moviesList: List<MoviesItem>) {
        start = end
        end += 10
        adapter.addAll(moviesList.subList(start, end))
    }

}








