package com.juangm.data.repository

import com.juangm.data.source.remote.MoviesRemoteSourceContract
import com.juangm.domain.models.Movie
import com.juangm.domain.repository.MoviesRepositoryContract
import timber.log.Timber

class MoviesRepository(private val moviesRemoteSource: MoviesRemoteSourceContract): MoviesRepositoryContract {

    /**
     * Repository cache
     */
    private var movies = mutableListOf<Movie>()
    private var nextPage: Int = 1

    override suspend fun getTopRatedMoviesAsync(loadMore: Boolean): List<Movie>? {
        if(loadMore || movies.isEmpty()) {
            val moviesResponse = moviesRemoteSource.getTopRatedMoviesFromApi(page = nextPage)
            moviesResponse?.let { response ->
                response.results?.let { movies.addAll(it.toMutableList()) }
                nextPage = response.page + 1
                Timber.i("Page ${response.page} retrieved. Next page: $nextPage. Movies size: ${movies.size}")
            }
        }
        return movies.toList()
    }
}