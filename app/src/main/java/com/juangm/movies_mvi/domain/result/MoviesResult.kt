package com.juangm.movies_mvi.domain.result

import com.juangm.movies_mvi.domain.models.Movie

sealed class MoviesResult: Result {
    object Loading: MoviesResult()
    data class Success(val movies: List<Movie>): MoviesResult()
    data class Failure(val errorMessage: String): MoviesResult()
}