package com.juangm.data.source.remote.api

import com.juangm.data.BuildConfig
import com.juangm.domain.models.MoviesResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesService {

    @GET( "movie/top_rated")
    fun getTopRatedMovies(
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY
    ): Deferred<Response<MoviesResponse>>
}