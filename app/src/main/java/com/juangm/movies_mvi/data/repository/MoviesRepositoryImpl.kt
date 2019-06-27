package com.juangm.movies_mvi.data.repository

import com.juangm.movies_mvi.data.source.remote.api.MoviesRemoteSource
import com.juangm.movies_mvi.domain.models.Movie
import kotlinx.coroutines.Deferred
import retrofit2.Response

class MoviesRepositoryImpl(private val moviesRemoteSource: MoviesRemoteSource): MoviesRepository {

    override suspend fun getTopRatedMoviesAsync(): List<Movie>? = moviesRemoteSource.getTopRatedMoviesFromApi()
}