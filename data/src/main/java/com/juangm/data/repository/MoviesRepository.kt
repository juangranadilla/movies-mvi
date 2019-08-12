package com.juangm.data.repository

import com.juangm.data.source.remote.MoviesRemoteSourceContract
import com.juangm.data.source.remote.api.MoviesResponse
import com.juangm.domain.models.Movie
import com.juangm.domain.repository.MoviesRepositoryContract
import timber.log.Timber

class MoviesRepository(private val moviesRemoteSource: MoviesRemoteSourceContract): MoviesRepositoryContract {

    /**
     * Repository cache
     */
    private var movies = mutableListOf<Movie>()
    private var nextPage: Int = 1

    override suspend fun getPopularMoviesAsync(loadMore: Boolean): List<Movie>? {
        return getMovies(loadMore) { moviesRemoteSource.getPopularMoviesFromApi(nextPage) }
    }

    override suspend fun getTopRatedMoviesAsync(loadMore: Boolean): List<Movie>? {
        return getMovies(loadMore) { moviesRemoteSource.getTopRatedMoviesFromApi(nextPage) }
    }

    override suspend fun getUpcomingMoviesAsync(loadMore: Boolean): List<Movie>? {
        return getMovies(loadMore) { moviesRemoteSource.getUpcomingMoviesFromApi(nextPage) }
    }

    private suspend fun getMovies(
        loadMore: Boolean,
        remoteSourceCall: suspend (nextPage: Int) -> MoviesResponse?
    ): List<Movie> {
        if(loadMore || movies.isEmpty()) {
            val moviesResponse =remoteSourceCall(nextPage)
            moviesResponse?.let { response ->
                response.results?.let { movies.addAll(it.toMutableList()) }
                nextPage = response.page + 1
                Timber.i("Page ${response.page} retrieved. Next page: $nextPage. Movies size: ${movies.size}")
            }
        }
        return movies.toList()
    }
}