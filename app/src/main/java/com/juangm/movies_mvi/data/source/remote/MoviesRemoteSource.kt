package com.juangm.movies_mvi.data.source.remote

import com.juangm.movies_mvi.domain.models.Movie

interface MoviesRemoteSource {
    suspend fun getTopRatedMoviesFromApi(): List<Movie>?
}