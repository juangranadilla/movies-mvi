package com.juangm.presentation.di

import com.juangm.presentation.viewmodel.MoviesViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel {
        MoviesViewModel(
            getTopRatedTopRatedMoviesUseCase = get(),
            loadMoreTopRatedTopRatedMoviesUseCase = get(),
            getPopularMoviesUseCase = get(),
            loadMorePopularMoviesUseCase = get(),
            getUpcomingMoviesUseCase = get(),
            loadMoreUpcomingMoviesUseCase = get()
        )
    }
}