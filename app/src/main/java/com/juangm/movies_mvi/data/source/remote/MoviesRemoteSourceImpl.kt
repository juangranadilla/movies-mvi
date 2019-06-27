package com.juangm.movies_mvi.data.source.remote

import com.juangm.movies_mvi.data.source.remote.api.MoviesRemoteSource
import com.juangm.movies_mvi.data.source.remote.api.MoviesService
import com.juangm.movies_mvi.domain.models.Movie
import kotlinx.coroutines.Deferred
import retrofit2.Response

class MoviesRemoteSourceImpl(private val moviesService: MoviesService): MoviesRemoteSource {

    override suspend fun getTopRatedMoviesFromApi(): List<Movie>? {
        val response = moviesService.getTopRatedMovies().await()
        return response.body()
    }
}