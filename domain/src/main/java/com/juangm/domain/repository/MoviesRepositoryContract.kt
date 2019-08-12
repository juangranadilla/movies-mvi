package com.juangm.domain.repository

import com.juangm.domain.models.Movie

interface MoviesRepositoryContract {
    suspend fun getPopularMoviesAsync(loadMore: Boolean): List<Movie>?
    suspend fun getTopRatedMoviesAsync(loadMore: Boolean): List<Movie>?
    suspend fun getUpcomingMoviesAsync(loadMore: Boolean): List<Movie>?
}