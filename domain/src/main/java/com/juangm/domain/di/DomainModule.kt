package com.juangm.domain.di

import com.juangm.domain.usecase.*
import org.koin.dsl.module

val domainModule = module {
    single { GetPopularMoviesUseCase(get()) }
    single { LoadMorePopularMoviesUseCase(get()) }
    single { GetTopRatedMoviesUseCase(get()) }
    single { LoadMoreTopRatedMoviesUseCase(get()) }
    single { GetUpcomingMoviesUseCase(get()) }
    single { LoadMoreUpcomingMoviesUseCase(get()) }
}