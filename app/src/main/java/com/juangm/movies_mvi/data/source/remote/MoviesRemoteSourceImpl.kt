package com.juangm.movies_mvi.data.source.remote

import com.juangm.movies_mvi.data.source.remote.api.MoviesService
import com.juangm.movies_mvi.domain.models.Movie

class MoviesRemoteSourceImpl(private val moviesService: MoviesService): MoviesRemoteSource {

    override suspend fun getTopRatedMoviesFromApi(): List<Movie>? {
        val response = moviesService.getTopRatedMovies().await()
        return response.body()?.results
    }
}