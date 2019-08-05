package com.juangm.data.source.remote

import com.juangm.domain.models.Movie

interface MoviesRemoteSourceContract {
    suspend fun getTopRatedMoviesFromApi(): List<Movie>?
}