package com.juangm.domain.di

import com.juangm.domain.usecase.TopRatedMoviesUseCase
import org.koin.dsl.module

val domainModule = module {
    single { TopRatedMoviesUseCase(get()) }
}