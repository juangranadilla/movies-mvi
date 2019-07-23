package com.juangm.movies_mvi.presentation.di

import com.juangm.movies_mvi.presentation.ui.movies.MoviesViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { MoviesViewModel(get()) }
}