package com.juangm.data.source.remote

import com.juangm.data.source.remote.api.MoviesResponse
import com.juangm.data.source.remote.api.MoviesService

class MoviesRemoteSource(private val moviesService: MoviesService): MoviesRemoteSourceContract {

    override suspend fun getTopRatedMoviesFromApi(page: Int): MoviesResponse? {
        val response = moviesService.getTopRatedMovies(page = page).await()
        return response.body()
    }
}