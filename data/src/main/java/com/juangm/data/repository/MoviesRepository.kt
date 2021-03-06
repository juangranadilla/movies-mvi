package com.juangm.data.repository

import com.juangm.data.source.remote.MoviesRemoteSourceContract
import com.juangm.data.source.remote.api.MoviesResponse
import com.juangm.domain.models.Movie
import com.juangm.domain.repository.MoviesRepositoryContract
import timber.log.Timber

class MoviesRepository(
    private val moviesRemoteSource: MoviesRemoteSourceContract
): BaseMoviesRepository(), MoviesRepositoryContract {

    /**
     * Repository cache
     */
    private var popularMovies = mutableListOf<Movie>()
    private var popularNextPage: Int = 1
    private var topRatedMovies = mutableListOf<Movie>()
    private var topRatedNextPage: Int = 1
    private var upcomingMovies = mutableListOf<Movie>()
    private var upcomingNextPage: Int = 1

    override suspend fun getPopularMoviesAsync(loadMore: Boolean): List<Movie>? {
        return getMovies(
            loadMore,
            popularMovies,
            moviesRemoteSource.getPopularMoviesFromApi(popularNextPage)
        ) {
            popularNextPage = it + 1
        }
    }

    override suspend fun getTopRatedMoviesAsync(loadMore: Boolean): List<Movie>? {
        return getMovies(
            loadMore,
            topRatedMovies,
            moviesRemoteSource.getTopRatedMoviesFromApi(topRatedNextPage)
        ) {
            topRatedNextPage = it + 1
        }
    }

    override suspend fun getUpcomingMoviesAsync(loadMore: Boolean): List<Movie>? {
        return getMovies(
            loadMore,
            upcomingMovies,
            moviesRemoteSource.getUpcomingMoviesFromApi(upcomingNextPage)
        ) {
            upcomingNextPage = it + 1
        }
    }
}