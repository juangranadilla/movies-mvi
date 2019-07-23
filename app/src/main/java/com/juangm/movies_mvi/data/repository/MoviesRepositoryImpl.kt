package com.juangm.movies_mvi.data.repository

import com.juangm.movies_mvi.data.source.remote.MoviesRemoteSource
import com.juangm.movies_mvi.domain.models.Movie

class MoviesRepositoryImpl(private val moviesRemoteSource: MoviesRemoteSource): MoviesRepository {

    override suspend fun getTopRatedMoviesAsync(): List<Movie>? = moviesRemoteSource.getTopRatedMoviesFromApi()
}