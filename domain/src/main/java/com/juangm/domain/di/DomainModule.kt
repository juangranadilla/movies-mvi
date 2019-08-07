package com.juangm.domain.di

import com.juangm.domain.usecase.LoadMoreTopRatedMoviesUseCase
import com.juangm.domain.usecase.GetTopRatedMoviesUseCase
import org.koin.dsl.module

val domainModule = module {
    single { GetTopRatedMoviesUseCase(get()) }
    single { LoadMoreTopRatedMoviesUseCase(get()) }
}