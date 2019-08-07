package com.juangm.domain.result

import com.juangm.domain.models.Movie

sealed class MoviesResult: Result {
    object Loading: MoviesResult()
    object LoadingMore: MoviesResult()
    data class Success(val movies: List<Movie>): MoviesResult()
    data class Failure(val errorMessage: String): MoviesResult()
}