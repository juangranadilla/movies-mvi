package com.juangm.movies_mvi.data.repository

import com.juangm.movies_mvi.domain.models.Movie

interface MoviesRepository {
    suspend fun getTopRatedMoviesAsync(): List<Movie>?
}