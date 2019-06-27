package com.juangm.movies_mvi.data.source.remote.api

import com.juangm.movies_mvi.BuildConfig
import com.juangm.movies_mvi.domain.models.Movie
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesService {

    @GET( "/movie/top_rated")
    fun getTopRatedMovies(
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY
    ): Deferred<Response<List<Movie>>>
}