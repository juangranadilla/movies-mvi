package com.juangm.data.source.remote

import com.juangm.data.source.remote.api.MoviesService
import com.juangm.domain.models.Movie

class MoviesRemoteSource(private val moviesService: MoviesService): MoviesRemoteSourceContract {

    override suspend fun getTopRatedMoviesFromApi(): List<Movie>? {
        val response = moviesService.getTopRatedMovies().await()
        return response.body()?.results
    }
}