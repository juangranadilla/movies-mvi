package com.example.movies_mvi.presentation.di

import com.example.movies_mvi.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_API_URL = "https://api.themoviedb.org/3/"

fun provideOkHttpClient(): OkHttpClient = OkHttpClient().newBuilder()
    .addInterceptor(HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
        else HttpLoggingInterceptor.Level.NONE
    })
    .build()

fun provideRetrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
    .baseUrl(BASE_API_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .client(client)
    .build()

fun provideMoviesService(retrofit: Retrofit): MoviesService = retrofit.create(MoviesService::class.java)