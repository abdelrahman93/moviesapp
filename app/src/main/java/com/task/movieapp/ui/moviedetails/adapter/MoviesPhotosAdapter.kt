package com.orange.offers.ui.home4g.offerlist.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.task.movieapp.R
import com.task.movieapp.data.model.flicker.PhotoItem
import com.task.movieapp.utilities.urlFlickerBuilder
import kotlinx.android.synthetic.main.item_poster_movie_list.view.*


class MoviesPhotosAdapter(
    private val moviesPhotosList: ArrayList<PhotoItem>

) :
    RecyclerView.Adapter<MoviesPhotosAdapter.MoviesViewHolder>() {

    override fun getItemCount() = moviesPhotosList.size

    fun addAll(newMoviesList: List<PhotoItem>) {
        moviesPhotosList.clear()
        moviesPhotosList.addAll(newMoviesList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_poster_movie_list, parent, false)
        return MoviesViewHolder(view)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(moviesPhotosList[position])
    }

    inner class MoviesViewHolder(private val rootView: View) : RecyclerView.ViewHolder(rootView) {
        fun bind(photoItem: PhotoItem) {

            Glide.with(this.rootView.context)
                .load(photoItem.urlFlickerBuilder())
                .placeholder(R.drawable.ic_logo)
                .into(this.rootView.ivPoster)

        }
    }


}