package com.task.movieapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.task.movieapp.R

class MoviesActivityHost : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)
    }
}