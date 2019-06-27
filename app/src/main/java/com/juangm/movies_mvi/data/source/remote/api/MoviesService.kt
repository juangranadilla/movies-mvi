package com.juangm.movies_mvi.data.source.remote

import com.juangm.movies_mvi.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesService {

    @GET( "/movie/top_rated")
    fun getTopRatedMovies(@Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY)
}