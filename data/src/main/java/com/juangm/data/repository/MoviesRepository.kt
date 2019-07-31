package com.juangm.data.repository

import com.juangm.data.source.remote.MoviesRemoteSourceContract
import com.juangm.domain.models.Movie
import com.juangm.domain.repository.MoviesRepositoryContract

class MoviesRepository(private val moviesRemoteSource: MoviesRemoteSourceContract):
    MoviesRepositoryContract {

    override suspend fun getTopRatedMoviesAsync(): List<Movie>? = moviesRemoteSource.getTopRatedMoviesFromApi()
}