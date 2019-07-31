package com.juangm.domain.repository

import com.juangm.domain.models.Movie

interface MoviesRepositoryContract {
    suspend fun getTopRatedMoviesAsync(): List<Movie>?
}