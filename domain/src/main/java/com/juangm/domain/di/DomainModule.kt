package com.juangm.domain.di

import com.juangm.domain.usecase.*
import org.koin.dsl.module

val domainModule = module {
    single { GetPopularMoviesUseCase(moviesRepository = get()) }
    single { LoadMorePopularMoviesUseCase(moviesRepository = get()) }
    single { GetTopRatedMoviesUseCase(moviesRepository = get()) }
    single { LoadMoreTopRatedMoviesUseCase(moviesRepository = get()) }
    single { GetUpcomingMoviesUseCase(moviesRepository = get()) }
    single { LoadMoreUpcomingMoviesUseCase(moviesRepository = get()) }
}