package com.juangm.data.source.remote

import com.juangm.data.source.remote.api.MoviesResponse

interface MoviesRemoteSourceContract {
    suspend fun getTopRatedMoviesFromApi(page: Int = 1): MoviesResponse?
}