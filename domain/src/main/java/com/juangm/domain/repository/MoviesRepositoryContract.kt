package com.juangm.domain.repository

import com.juangm.domain.models.Movie

interface MoviesRepositoryContract {
    suspend fun getTopRatedMoviesAsync(loadMore: Boolean): List<Movie>?
}