package com.juangm.movies_mvi.presentation.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.juangm.movies_mvi.BuildConfig
import com.juangm.movies_mvi.data.repository.MoviesRepository
import com.juangm.movies_mvi.data.repository.MoviesRepositoryImpl
import com.juangm.movies_mvi.data.source.remote.MoviesRemoteSourceImpl
import com.juangm.movies_mvi.data.source.remote.MoviesRemoteSource
import com.juangm.movies_mvi.data.source.remote.api.MoviesService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_API_URL = "https://api.themoviedb.org/3/"

val dataModule = module {
    single { provideOkHttpClient() }
    single { provideRetrofit(get()) }
    single { provideMoviesService(get()) }
    single { MoviesRemoteSourceImpl(get()) as MoviesRemoteSource }
    single { MoviesRepositoryImpl(get()) as MoviesRepository }
}

fun provideOkHttpClient(): OkHttpClient = OkHttpClient().newBuilder()
    .addInterceptor(HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
        else HttpLoggingInterceptor.Level.NONE
    })
    .build()

fun provideRetrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
    .baseUrl(BASE_API_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(CoroutineCallAdapterFactory())
    .client(client)
    .build()

fun provideMoviesService(retrofit: Retrofit): MoviesService = retrofit.create(MoviesService::class.java)