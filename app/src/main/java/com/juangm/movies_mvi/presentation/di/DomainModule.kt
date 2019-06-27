package com.juangm.movies_mvi.presentation.di

import com.juangm.movies_mvi.domain.usecase.TopRatedMoviesUseCase
import org.koin.dsl.module

val domainModule = module {
    single { TopRatedMoviesUseCase(get()) }
}