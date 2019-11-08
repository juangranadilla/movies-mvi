package com.juangm.data.repository

import com.juangm.data.source.remote.api.MoviesResponse
import com.juangm.domain.models.Movie
import timber.log.Timber

abstract class BaseMoviesRepository {

    protected fun getMovies(
        loadMore: Boolean,
        movies: MutableList<Movie>,
        moviesResponse: MoviesResponse?,
        onPageRetrieved: (actualPage: Int) -> Unit
    ): List<Movie> {
        if(loadMore || movies.isEmpty()) {
            moviesResponse?.let { response ->
                response.results?.let { movies.addAll(it.toMutableList()) }
                Timber.i("Page ${response.page} retrieved. Movies size: ${movies.size}")
                onPageRetrieved(response.page)
            }
        }
        return movies.toList()
    }
}