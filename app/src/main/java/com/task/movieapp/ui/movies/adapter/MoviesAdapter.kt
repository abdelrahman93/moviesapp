package com.orange.offers.ui.home4g.offerlist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.task.movieapp.R
import com.task.movieapp.data.model.MoviesItem
import kotlinx.android.synthetic.main.item_movie.view.*


class MoviesAdapter(private val moviesList: ArrayList<MoviesItem>) :
    RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {

    override fun getItemCount() = moviesList.size

    fun addAll(newMoviesList: List<MoviesItem>) {
        moviesList.clear()
        moviesList.addAll(newMoviesList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie, parent, false)
        return MoviesViewHolder(view)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(moviesList[position])
    }

    inner class MoviesViewHolder(private val rootView: View) : RecyclerView.ViewHolder(rootView) {
        fun bind(moviesItem: MoviesItem) {
            this.rootView.tvMovieTitle.text = moviesItem.title
            this.rootView.tvMovieYear.text = String.format(rootView.resources.getString(R.string.year), moviesItem.year)
            this.rootView.tvMovieRating.text = String.format(rootView.resources.getString(R.string.rating), moviesItem.rating)
            this.rootView.tvMovieGenres.text = moviesItem.genres?.joinToString(separator = " , ")
            this.rootView.tvMovieCast.text = String.format(rootView.resources.getString(R.string.cast), moviesItem.cast?.joinToString(separator = " , "))
        }
    }
}