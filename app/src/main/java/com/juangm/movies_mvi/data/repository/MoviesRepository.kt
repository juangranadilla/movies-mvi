package com.juangm.movies_mvi.data.repository

import com.juangm.movies_mvi.domain.models.Movie
import kotlinx.coroutines.Deferred
import retrofit2.Response

interface MoviesRepository {
    suspend fun getTopRatedMoviesAsync(): List<Movie>?
}