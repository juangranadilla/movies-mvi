package com.juangm.movies_mvi.data.source.remote.api

import com.juangm.movies_mvi.domain.models.Movie
import kotlinx.coroutines.Deferred
import retrofit2.Response

interface MoviesRemoteSource {
    suspend fun getTopRatedMoviesFromApi(): List<Movie>?
}