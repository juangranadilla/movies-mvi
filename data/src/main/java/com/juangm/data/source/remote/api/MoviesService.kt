package com.juangm.data.source.remote.api

import com.juangm.data.BuildConfig
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesService {

    @GET( "movie/popular")
    fun getPopularMoviesAsync(
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
        @Query("page") page: Int = 1
    ): Deferred<Response<MoviesResponse>>

    @GET( "movie/top_rated")
    fun getTopRatedMoviesAsync(
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
        @Query("page") page: Int = 1
    ): Deferred<Response<MoviesResponse>>

    @GET( "movie/upcoming")
    fun getUpcomingMoviesAsync(
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
        @Query("page") page: Int = 1
    ): Deferred<Response<MoviesResponse>>
}