package com.juangm.moviesmvi.ui.movies.list

import android.widget.ImageView
import com.juangm.domain.models.Movie

interface MovieClickListener {
    fun onMovieClick(movie: Movie, movieImage: ImageView)
}