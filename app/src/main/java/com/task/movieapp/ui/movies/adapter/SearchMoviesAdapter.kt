package com.orange.offers.ui.home4g.offerlist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.task.movieapp.R
import com.task.movieapp.data.model.MoviesByYear
import kotlinx.android.synthetic.main.item_search_movie.view.*
import kotlinx.android.synthetic.main.search_list_layout.view.*


class SearchMoviesAdapter(private val moviesSearchList: ArrayList<MoviesByYear>) :
    RecyclerView.Adapter<SearchMoviesAdapter.MoviesViewHolder>() {

    override fun getItemCount() = moviesSearchList.size

    fun addAll(newMoviesList: List<MoviesByYear>) {
        moviesSearchList.clear()
        moviesSearchList.addAll(newMoviesList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_search_movie, parent, false)
        return MoviesViewHolder(view)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(moviesSearchList[position])
    }

    inner class MoviesViewHolder(private val rootView: View) : RecyclerView.ViewHolder(rootView) {
        fun bind(moviesByYear: MoviesByYear) {
            this.rootView.tvSearchYear.text = moviesByYear.year.toString()
            var adapter = MoviesAdapter(ArrayList())
            this.rootView.rvMoviesListByYear.adapter = adapter
            adapter.addAll(moviesByYear.moviesListByYear)

        }
    }
}