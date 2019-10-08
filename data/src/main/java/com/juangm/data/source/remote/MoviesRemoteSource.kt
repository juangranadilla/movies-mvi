package com.juangm.data.source.remote

import com.juangm.data.source.remote.api.MoviesResponse
import com.juangm.data.source.remote.api.MoviesService

class MoviesRemoteSource(private val moviesService: MoviesService): MoviesRemoteSourceContract {

    override suspend fun getPopularMoviesFromApi(page: Int): MoviesResponse? {
        val response = moviesService.getPopularMoviesAsync(page = page)
        return response.body()
    }

    override suspend fun getTopRatedMoviesFromApi(page: Int): MoviesResponse? {
        val response = moviesService.getTopRatedMoviesAsync(page = page)
        return response.body()
    }

    override suspend fun getUpcomingMoviesFromApi(page: Int): MoviesResponse? {
        val response = moviesService.getUpcomingMoviesAsync(page = page)
        return response.body()
    }
}