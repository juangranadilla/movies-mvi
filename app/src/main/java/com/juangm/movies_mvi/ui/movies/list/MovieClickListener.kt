package com.juangm.movies_mvi.ui.movies.list

import android.widget.ImageView
import com.juangm.domain.models.Movie

interface MovieClickListener {
    fun onMovieClick(movie: Movie, movieImage: ImageView, position: Int)
}