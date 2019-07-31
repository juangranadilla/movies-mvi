package com.juangm.movies_mvi.di

import com.juangm.movies_mvi.ui.movies.MoviesViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { MoviesViewModel(get()) }
}