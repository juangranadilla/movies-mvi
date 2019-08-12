package com.juangm.data.source.remote

import com.juangm.data.source.remote.api.MoviesResponse
import com.juangm.data.source.remote.api.MoviesService

class MoviesRemoteSource(private val moviesService: MoviesService): MoviesRemoteSourceContract {

    override suspend fun getPopularMoviesFromApi(page: Int): MoviesResponse? {
        val response = moviesService.getPopularMoviesAsync(page = page).await()
        return response.body()
    }

    override suspend fun getTopRatedMoviesFromApi(page: Int): MoviesResponse? {
        val response = moviesService.getTopRatedMoviesAsync(page = page).await()
        return response.body()
    }

    override suspend fun getUpcomingMoviesFromApi(page: Int): MoviesResponse? {
        val response = moviesService.getUpcomingMoviesAsync(page = page).await()
        return response.body()
    }
}